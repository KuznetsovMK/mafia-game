package state.impl;

import game.Game;
import lombok.AllArgsConstructor;
import role.ActionType;
import state.State;

@AllArgsConstructor
public class NightState implements State {
    private Game game;

    @Override
    public void nextGameLevel() {
        System.out.println("Наступила ночь. Все граждане уснули.");
        game.setState(game.getMafiaShootingState());
    }

    @Override
    public void info() {

    }

    @Override
    public void vote(String targetName, String initiatorName, ActionType actionType) {

    }
}
