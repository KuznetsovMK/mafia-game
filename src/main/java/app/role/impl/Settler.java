package app.role.impl;

import app.game.GameInstance;
import app.player.Player;
import app.role.Judge;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Settler implements Judge {
    private GameInstance gameInstance;
    private List<Player> settlerPlayers;
    private static final String INFO = "Cуд вынес решение. %s/%s - выбывает";

    @Override
    public void judge(String targetName) {
        var target = gameInstance.getPlayerByName().get(targetName);
        target.setAlive(false);
        System.out.println(INFO.formatted(target.getName(), target.getRole()));
    }

    public void addPlayer(Player player) {
        settlerPlayers.add(player);
    }

    public Settler(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
        settlerPlayers = new ArrayList<>();
    }
}
