//package app.role.impl;
//
//import app.command.ShootCommand;
//import app.game.GameInstance;
//import app.player.Player;
//import app.role.Looking;
//import app.role.Shooter;
//import lombok.Data;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Data
//public class Detective implements Shooter, Looking {
//    private GameInstance gameInstance;
//    private Player detectivePlayer;
//    private Map<String, String> targetRoleByName;
//    private static final String INFO = "Лидер сделал свой ход.";
//
//    @Override
//    public void shoot(Player target) {
//        gameInstance.getShootingQueue().add(new ShootCommand(detectivePlayer, target));
//        System.out.println(INFO);
//    }
//
//    @Override
//    public void look(String targetName) {
//        var target = gameInstance.getPlayerByName().get(targetName);
//        targetRoleByName.put(targetName, target.getRole());
//
//        System.out.println(INFO);
//    }
//
//    public void addPlayer(Player player) {
//        detectivePlayer = player;
//    }
//
//    public Detective(GameInstance gameInstance) {
//        this.gameInstance = gameInstance;
//        targetRoleByName = new HashMap<>();
//    }
//}
