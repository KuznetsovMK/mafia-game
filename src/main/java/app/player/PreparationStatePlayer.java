package app.player;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PreparationStatePlayer implements Player {
    private String name;
    private boolean ready;
}
