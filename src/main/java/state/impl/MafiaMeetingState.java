package state.impl;

import game.Game;
import lombok.AllArgsConstructor;
import role.ActionType;
import state.State;

@AllArgsConstructor
public class MafiaMeetingState implements State {
    private Game game;

    @Override
    public void nextGameLevel() {
        System.out.println("Начинается знакомство мафии друг с другом...");
        System.out.println("Мафия познакомилась.");
        game.setState(game.getDailyResultState());
    }

    @Override
    public void info() {

    }

    @Override
    public void vote(String targetName, String initiatorName, ActionType actionType) {

    }
}
