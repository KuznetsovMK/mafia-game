package app.game;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Game {
    @Value("${game.instance.id}")
    private UUID gameId;
    private final Map<UUID, GameInstance> games = new HashMap<>();

    @PostConstruct
    public void init() {
        var game = new GameInstance(gameId);
        games.put(game.getId(), game);

        game.nextGameLevel();
    }

    public GameInstance getGameInstance(UUID gameId) {
        return games.get(gameId);
    }
}
