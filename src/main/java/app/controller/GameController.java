package app.controller;

import app.game.Game;
import app.game.GameMessage;
import app.player.GameInstancePlayer;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Objects;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class GameController {
    private final Game game;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/{gameId}-sendMessage")
    public void sendMessage(
            @DestinationVariable String gameId,
            @Payload GameMessage gameMessage
    ) {
        var instance = game.getGameInstance(UUID.fromString(gameMessage.getGameId()));
        instance.vote(gameMessage.getTarget(), gameMessage.getSender(), gameMessage.getType());

        var path = "/topic/%s-public".formatted(gameId);
        messagingTemplate.convertAndSend(path, instance.info());

        instance.nextGameLevel();
    }

    @MessageMapping("/{gameId}-addUser")
    public void addUser(
            @DestinationVariable String gameId,
            @Payload GameMessage gameMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        var instance = game.getGameInstance(UUID.fromString(gameMessage.getGameId()));
        instance.getPlayerByName().put(gameMessage.getSender(), createNewPlayer(gameMessage.getSender()));

        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", gameMessage.getSender());

        var path = "/topic/%s-public".formatted(gameId);
        messagingTemplate.convertAndSend(path, gameMessage);
    }

    private GameInstancePlayer createNewPlayer(String name) {
        return GameInstancePlayer.builder()
                .name(name)
                .build();
    }
}
