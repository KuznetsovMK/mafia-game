package state.impl;

import game.Game;
import lombok.AllArgsConstructor;
import role.ActionType;
import state.State;

@AllArgsConstructor
public class DailyMeetingState implements State {
    private static final String INFO = "Местные жители встречаются, чтобы обсудить последние новости";
    private final Game game;

    @Override
    public void nextGameLevel() {
        System.out.println(INFO);
        game.setState(game.getLawsuitState());
    }

    @Override
    public void info() {
        //не нужен
    }

    @Override
    public void vote(String targetName, String initiatorName, ActionType actionType) {
        //не нужен
    }
}
