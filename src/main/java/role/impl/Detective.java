package role.impl;

import command.ShootCommand;
import game.Game;
import lombok.Data;
import player.Player;
import role.Looking;
import role.Shooter;

import java.util.HashMap;
import java.util.Map;

@Data
public class Detective implements Shooter, Looking {
    private Game game;
    private Player detectivePlayer;
    private Map<String, String> targetRoleByName;
    private static final String INFO = "Лидер сделал свой ход.";

    @Override
    public void shoot(Player target) {
        game.getShootingQueue().add(new ShootCommand(detectivePlayer, target));
        System.out.println(INFO);
    }

    @Override
    public void look(String targetName) {
        var target = game.getPlayerByName().get(targetName);
        targetRoleByName.put(targetName, target.getRole());

        System.out.println(INFO);
    }

    public void addPlayer(Player player) {
        detectivePlayer = player;
    }

    public Detective(Game game) {
        this.game = game;
        targetRoleByName = new HashMap<>();
    }
}
