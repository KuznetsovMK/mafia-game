//package app.state.impl;
//
//import app.game.GameInstance;
//import app.role.consts.WinnerType;
//import app.service.GameOverService;
//import app.state.State;
//import lombok.AllArgsConstructor;
//
//@AllArgsConstructor
//public class DailyResultState implements State {
//    private static final String INFO = "Наступил день. Все просыпаются.";
//    private static final String NO_SHOOTING = "Ночь прошла без проишествий.";
//    private static final String SHOOTING = "Ночью произошли следующие события: ";
//    private GameInstance gameInstance;
//
//    @Override
//    public void nextGameLevel() {
//        System.out.println(INFO);
//
//        if (gameInstance.getShootingQueue().isEmpty()) {
//            System.out.println(NO_SHOOTING);
//
//        } else {
//            System.out.println(SHOOTING);
//            executeCommand();
//        }
//
//        goNext();
//    }
//
//    private void executeCommand() {
//        while (!gameInstance.getShootingQueue().isEmpty()) {
//            var shootingCommand = gameInstance.getShootingQueue().poll();
//            shootingCommand.execute();
//        }
//    }
//
//    private void goNext() {
//        var winner = GameOverService
//                .findWinner(gameInstance.getPlayerByName().values().stream().toList());
//
//        if (winner != null) {
//            gameOverLevel(winner);
//
//        } else {
//            goNextGameLevel();
//        }
//    }
//
//    private void gameOverLevel(WinnerType winner) {
//        gameInstance.setWinner(winner);
//        gameInstance.setState(gameInstance.getGameOverState());
//        gameInstance.nextGameLevel();
//    }
//
//    private void goNextGameLevel() {
//        gameInstance.setState(gameInstance.getDailyMeetingState());
//        gameInstance.nextGameLevel();
//    }
//}
