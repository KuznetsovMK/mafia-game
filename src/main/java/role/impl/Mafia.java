package role.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import player.Player;
import role.Killer;
import role.Voter;

@Data
@AllArgsConstructor
public class Mafia extends Role implements Voter, Killer {
    private Player player;

    @Override
    public void kill() {

    }

    @Override
    public void vote(Player target) {
        System.out.println("%s voted for a %s".formatted(player.getName(), target.getName()));
    }
}
