//package state.impl;
//
//import app.game.GameInstance;
//import org.junit.jupiter.api.Test;
//import app.player.Player;
//import app.role.consts.ActionType;
//
//import java.util.List;
//
//class DailyResultStateTest {
//    @Test
//    void test1() {
//        var player1 = Player.builder()
//                .name("Player_1")
//                .ready(true)
//                .build();
//
//        var player2 = Player.builder()
//                .name("Player_2")
//                .ready(true)
//                .build();
//
//        var player3 = Player.builder()
//                .name("Player_3")
//                .ready(true)
//                .build();
//
//        var player4 = Player.builder()
//                .name("Player_4")
//                .ready(true)
//                .build();
//
//        var player5 = Player.builder()
//                .name("Player_5")
//                .ready(true)
//                .build();
//
//
//        var game = new GameInstance();
//        addPlayers(game, List.of(player1, player2, player3, player4, player5));
//        game.nextGameLevel();
//
////        game.setSettler(new Settler(game, List.of(player1)));
////        game.setDetective(new Detective(game, player2));
////        game.setMafia(new Mafia(game, List.of(player3, player4, player5)));
//
//        game.setState(game.getMafiaShootingState());
//        game.nextGameLevel();
//
//        game.vote("Player_1", "Player_3", ActionType.SHOOT);
//        game.vote("Player_2", "Player_4", ActionType.SHOOT);
//        game.vote("Player_2", "Player_5", ActionType.SHOOT);
//
//
//        game.vote("Player_5", "Player_2", ActionType.SHOOT);
//
//
//        game.gameInfo();
//    }
//
//    private void addPlayers(GameInstance gameInstance, List<Player> players) {
//        players.forEach(player -> gameInstance.getPlayerByName().put(player.getName(), player));
//    }
//}