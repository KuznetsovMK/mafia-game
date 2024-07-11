package role.impl;

import command.ShootCommand;
import game.Game;
import lombok.Data;
import player.Player;
import role.RoleNameConst;
import role.Shooter;

import java.util.ArrayList;
import java.util.List;

@Data
public class Mafia implements Shooter {
    private Game game;
    private List<Player> mafiaPlayers;
    private static final String INFO = "Прозвучал выстрел...";

    @Override
    public void shoot(Player target) {
        var mafiaPlayer = mafiaPlayers.stream()
                .filter(Player::isAlive)
                .findFirst()
                .orElse(new Player());

        game.getShootingQueue().add(new ShootCommand(mafiaPlayer, target));
        System.out.println(INFO);
    }

    public Mafia(Game game, List<Player> players) {
        this.game = game;
        mafiaPlayers = new ArrayList<>();

        players.forEach(player -> {
            mafiaPlayers.add(player);
            player.setRole(RoleNameConst.MAFIA);
            player.setAlive(true);
        });
    }
}
