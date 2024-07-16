//package app.role.impl;
//
//import app.game.GameInstance;
//import app.role.Shooter;
//import app.command.ShootCommand;
//import lombok.Data;
//import app.player.Player;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//public class Mafia implements Shooter {
//    private GameInstance gameInstance;
//    private List<Player> mafiaPlayers;
//    private static final String INFO = "Прозвучал выстрел...";
//
//    @Override
//    public void shoot(Player target) {
//        var mafiaPlayer = mafiaPlayers.stream()
//                .filter(Player::isAlive)
//                .findFirst()
//                .orElse(new Player());
//
//        gameInstance.getShootingQueue().add(new ShootCommand(mafiaPlayer, target));
//        System.out.println(INFO);
//    }
//
//    public void addPlayer(Player player) {
//        mafiaPlayers.add(player);
//    }
//
//    public Mafia(GameInstance gameInstance) {
//        this.gameInstance = gameInstance;
//        mafiaPlayers = new ArrayList<>();
//    }
//}
