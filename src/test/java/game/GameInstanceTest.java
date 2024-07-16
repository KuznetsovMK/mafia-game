//package game;
//
//import app.game.GameInstance;
//import org.junit.jupiter.api.Test;
//import app.player.Player;
//import app.role.consts.ActionType;
//import app.role.consts.RoleNameConst;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class GameInstanceTest {
//    @Test
//    void test1() {
//        var game = new GameInstance();
//
//        addPlayers(game, initPlayers());
//        game.nextGameLevel();
//
//        List<Player> players = game.getPlayerByName().values().stream().toList();
//
//        allReady(game, players);
//
//
//        mafiaMeeting(game, players);
//
//        while (game.getWinner() == null) {
//            judge(game, players);
//            mafiaShooting(game, players);
//            leaderShooting(game, players);
//        }
//
//        game.gameInfo();
//
//    }
//
//    private List<Player> initPlayers() {
//        var result = new ArrayList<Player>();
//        var name = "Player_";
//
//        for (int i = 1; i < 30; i++) {
//            result.add(Player.builder()
//                    .name(name + i)
//                    .build());
//        }
//
//        return result;
//    }
//
//    private void addPlayers(GameInstance gameInstance, List<Player> players) {
//        players.forEach(player -> gameInstance.getPlayerByName().put(player.getName(), player));
//    }
//
//    private void allReady(GameInstance gameInstance, List<Player> players) {
//        players.forEach(player -> gameInstance.vote(null, player.getName(), ActionType.READY));
//    }
//
//    private void mafiaMeeting(GameInstance gameInstance, List<Player> players) {
//        var target = players.stream()
//                .filter(player -> !RoleNameConst.MAFIA.equals(player.getRole()))
//                .findFirst()
//                .orElse(null);
//
//        players.stream()
//                .filter(player -> RoleNameConst.MAFIA.equals(player.getRole()))
//                .forEach(player -> gameInstance.vote(target.getName(), player.getName(), ActionType.SHOOT));
//    }
//
//    private void judge(GameInstance gameInstance, List<Player> players) {
//        var target = players.stream()
//                .filter(Player::isAlive)
//                .findFirst()
//                .orElse(null);
//
//        players.stream()
//                .filter(Player::isAlive)
//                .forEach(player -> gameInstance.vote(target.getName(), player.getName(), ActionType.JUDGE));
//    }
//
//    private void mafiaShooting(GameInstance gameInstance, List<Player> players) {
//        var target = players.stream()
//                .filter(Player::isAlive)
//                .filter(player -> !RoleNameConst.MAFIA.equals(player.getRole()))
//                .findFirst()
//                .orElse(null);
//
//        players.stream()
//                .filter(player -> RoleNameConst.MAFIA.equals(player.getRole()))
//                .forEach(player -> gameInstance.vote(target.getName(), player.getName(), ActionType.SHOOT));
//    }
//
//    private void leaderShooting(GameInstance gameInstance, List<Player> players) {
//        var target = players.stream()
//                .filter(Player::isAlive)
//                .filter(player -> !RoleNameConst.DETECTIVE.equals(player.getRole()))
//                .findFirst()
//                .orElse(null);
//
//        players.stream()
//                .filter(player -> RoleNameConst.DETECTIVE.equals(player.getRole()))
//                .forEach(player -> gameInstance.vote(target.getName(), player.getName(), ActionType.SHOOT));
//    }
//}