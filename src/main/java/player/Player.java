package player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private String name;
    private boolean ready;
    private String role;
    private boolean alive;

    public void sendMessage(String msg) {
        System.out.println("Send message for %:".formatted(name));
        System.out.println(msg);
    }
}
