package app.player;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameInstancePlayer {
    private String name;
    private boolean ready;
    private String role;
    private boolean alive;
}
