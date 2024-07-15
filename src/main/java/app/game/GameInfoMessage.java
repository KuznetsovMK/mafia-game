package app.game;

import app.player.Player;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GameInfoMessage {
    private String stateName;
    private String stateInfo;
    private List<Player> preparationStatePlayers;
}
