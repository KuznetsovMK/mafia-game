package role.impl;

import game.Game;
import lombok.Data;
import player.Player;
import role.Judge;
import role.RoleNameConst;

import java.util.ArrayList;
import java.util.List;

@Data
public class Settler implements Judge {
    private Game game;
    private List<Player> settlerPlayers;
    private static final String INFO = "Cуд вынес решение. Игрок %s выбывает";

    @Override
    public void judge(String targetName) {
        var target = game.getPlayerByName().get(targetName);
        target.setAlive(false);
        System.out.println(INFO.formatted(target.getName()));
    }

    public Settler(Game game, List<Player> players) {
        this.game = game;
        settlerPlayers = new ArrayList<>();

        players.forEach(player -> {
            settlerPlayers.add(player);
            player.setRole(RoleNameConst.SETTLER);
            player.setAlive(true);
        });
    }
}
