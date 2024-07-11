package state.impl;

import game.Game;
import lombok.AllArgsConstructor;
import role.WinnerType;
import service.GameOverService;
import state.State;

@AllArgsConstructor
public class DailyResultState implements State {
    private static final String INFO = "Наступил день. Все просыпаются.";
    private static final String NO_SHOOTING = "Ночь прошла без проишествий.";
    private static final String SHOOTING = "Ночью произошли следующие события: ";
    private Game game;

    @Override
    public void nextGameLevel() {
        System.out.println(INFO);

        if (game.getShootingQueue().isEmpty()) {
            System.out.println(NO_SHOOTING);

        } else {
            System.out.println(SHOOTING);
            executeCommand();
        }

        goNext();
    }

    private void executeCommand() {
        while (!game.getShootingQueue().isEmpty()) {
            var shootingCommand = game.getShootingQueue().poll();
            shootingCommand.execute();
        }
    }

    private void goNext() {
        var winner = GameOverService
                .findWinner(game.getPlayerByName().values().stream().toList());

        if (winner != null) {
            gameOverLevel(winner);

        } else {
            goNextGameLevel();
        }
    }

    private void gameOverLevel(WinnerType winner) {
        game.setWinner(winner);
        game.setState(game.getGameOverState());
        game.nextGameLevel();
    }

    private void goNextGameLevel() {
        game.setState(game.getDailyMeetingState());
        game.nextGameLevel();
    }
}
