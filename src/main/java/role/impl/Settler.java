package role.impl;

import lombok.Data;
import player.Player;
import role.Voter;

@Data
public class Settler extends Role implements Voter {
    private Player player;

    @Override
    public void vote(Player target) {
        System.out.println("%s voted for a %s".formatted(player.getName(), target.getName()));
    }

    public Settler(Player player) {
        this.player = player;
        player.setRole(this);
        player.setAlive(true);
    }

    @Override
    public String toString() {
        return "Settler{" +
                "player=" + player.getName() +
                '}';
    }
}
