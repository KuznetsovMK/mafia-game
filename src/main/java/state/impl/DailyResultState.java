package state.impl;

import game.Game;
import lombok.AllArgsConstructor;
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

        goNextGameLevel();
    }

    private void executeCommand() {
        while (!game.getShootingQueue().isEmpty()) {
            var shootingCommand = game.getShootingQueue().poll();
            shootingCommand.execute();
        }
    }

    private void goNextGameLevel() {
        game.setState(game.getDailyMeetingState());
        game.nextGameLevel();
    }
}
