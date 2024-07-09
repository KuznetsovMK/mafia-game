package state.impl;

import game.Game;
import lombok.AllArgsConstructor;
import state.State;

@AllArgsConstructor
public class DailyResultState implements State {
    private Game game;

    @Override
    public void nextGameLevel() {
        System.out.println("Наступил день. Все просыпаются.");
        if (game.getNightResults().isEmpty()) {
            System.out.println("Ночь прошла спокойно");
        } else {
            System.out.println("За предыдущую ночь произошли следующие события: ");
            game.getNightResults().forEach(System.out::println);
        }

        if (game.isGameOver()) {
            game.setState(game.getGameOverState());
        } else {
            game.setState(game.getDailyMeetingState());
            game.getNightResults().clear();
        }
    }
}
