package app.game;

import app.role.ActionType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameMessage {
    private String gameId;
    private String sender;
    private String target;
    private ActionType type;
}
