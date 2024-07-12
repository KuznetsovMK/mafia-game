package command;

import lombok.AllArgsConstructor;
import player.Player;

@AllArgsConstructor
public class ShootCommand implements Command {
    private Player initiator;
    private Player target;

    @Override
    public void execute() {
        if (initiator.isAlive() && target.isAlive()) {
            target.setAlive(false);
            System.out.println("%s/%s - убит".formatted(target.getName(), target.getRole()));
        }
    }
}
