package role.impl;

import game.Game;
import lombok.Data;
import player.Player;
import role.Judge;

import java.util.ArrayList;
import java.util.List;

@Data
public class Settler implements Judge {
    private Game game;
    private List<Player> settlerPlayers;
    private static final String INFO = "Cуд вынес решение. %s/%s - выбывает";

    @Override
    public void judge(String targetName) {
        var target = game.getPlayerByName().get(targetName);
        target.setAlive(false);
        System.out.println(INFO.formatted(target.getName(), target.getRole()));
    }

    public void addPlayer(Player player) {
        settlerPlayers.add(player);
    }

    public Settler(Game game) {
        this.game = game;
        settlerPlayers = new ArrayList<>();
    }
}
