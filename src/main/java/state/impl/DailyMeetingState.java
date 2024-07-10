package state.impl;

import game.Game;
import lombok.AllArgsConstructor;
import role.ActionType;
import state.State;

@AllArgsConstructor
public class DailyMeetingState implements State {
    private final Game game;

    @Override
    public void nextGameLevel() {
        System.out.println("Местные жители встречаются, чтобы обсудить последние новости");
        game.setState(game.getLawsuitState());
    }

    @Override
    public void info() {

    }

    @Override
    public void vote(String targetName, String initiatorName, ActionType actionType) {

    }
}
