package role.impl;

import game.Game;
import lombok.Data;
import player.Player;
import role.Looking;
import role.RoleNameConst;
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
        //todo команда выстрела. Изменение поля isAlive произойдёт
        // в момент выполнения команды
        target.setAlive(false);
        System.out.println(INFO);
    }

    @Override
    public void look(String targetName) {
        var target = game.getPlayerByName().get(targetName);
        targetRoleByName.put(targetName, target.getRole());

        System.out.println(INFO);
    }

    public Detective(Game game, Player player) {
        this.game = game;
        this.targetRoleByName = new HashMap<>();

        this.detectivePlayer = player;
        player.setRole(RoleNameConst.DETECTIVE);
        player.setAlive(true);
    }
}
