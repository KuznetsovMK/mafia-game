package app.state.impl;

import app.game.GameInstance;
import app.state.State;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DailyMeetingState implements State {
    private static final String INFO = "Местные жители встречаются, чтобы обсудить последние новости.";
    private final GameInstance gameInstance;

    @Override
    public void nextGameLevel() {
        System.out.println(INFO);
        //добавить таймаут (1-2 сек)
        goNextGameLevel();
    }

    private void goNextGameLevel() {
        gameInstance.setState(gameInstance.getLawsuitState());
        gameInstance.nextGameLevel();
    }
}
