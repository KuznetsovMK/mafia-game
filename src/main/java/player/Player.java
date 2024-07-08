package player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import role.impl.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private String name;
    private boolean ready;
    private Role role;
    private boolean alive;
}
