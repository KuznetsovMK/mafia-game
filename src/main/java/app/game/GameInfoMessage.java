package app.game;

import app.player.Player;
import app.state.StateName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GameInfoMessage {
    private StateName stateName;
    private String stateInfo;
    private List<Player> preparationStatePlayers;
}
