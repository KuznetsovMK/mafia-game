package state.impl;

import game.Game;
import lombok.AllArgsConstructor;
import role.ActionType;
import state.State;

@AllArgsConstructor
public class GameOverState implements State {
    private Game game;

    @Override
    public void nextGameLevel() {
        System.out.println("Игра окончена.");
        game.setState(game.getPreparationState());
    }

    @Override
    public void info() {

    }

    @Override
    public void vote(String targetName, String initiatorName, ActionType actionType) {

    }
}
