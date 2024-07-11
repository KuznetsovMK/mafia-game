package state.impl;

import game.Game;
import lombok.AllArgsConstructor;
import state.State;

@AllArgsConstructor
public class DailyMeetingState implements State {
    private static final String INFO = "Местные жители встречаются, чтобы обсудить последние новости.";
    private final Game game;

    @Override
    public void nextGameLevel() {
        System.out.println(INFO);
        //добавить таймаут (1-2 сек)
        goNextGameLevel();
    }

    private void goNextGameLevel() {
        game.setState(game.getLawsuitState());
        game.nextGameLevel();
    }
}
