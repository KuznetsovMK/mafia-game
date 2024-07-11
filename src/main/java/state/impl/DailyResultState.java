package state.impl;

import game.Game;
import lombok.AllArgsConstructor;
import role.ActionType;
import state.State;

@AllArgsConstructor
public class DailyResultState implements State {
    private static final String INFO = "Наступил день. Все просыпаются.";
    private static final String SHOOTING = "Ночью произошли следующие события: ";
    private static final String NO_SHOOTING = "Ночь прошла без проишествий.";
    private Game game;

    @Override
    public void nextGameLevel() {
        System.out.println(INFO);

        if (game.getShootingQueue().isEmpty()) {
            System.out.println(NO_SHOOTING);
        } else {
            System.out.println(SHOOTING);

            while (!game.getShootingQueue().isEmpty()) {
                var command = game.getShootingQueue().poll();
                command.execute();
            }
        }

        goNextGameLevel();
    }

    @Override
    public void info() {
        //не нужен
    }

    @Override
    public void vote(String targetName, String initiatorName, ActionType actionType) {
        //не нужен
    }

    private void goNextGameLevel() {
        game.setState(game.getDailyMeetingState());
        game.nextGameLevel();
    }
}
