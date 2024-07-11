package state.impl;

import game.Game;
import lombok.AllArgsConstructor;
import role.ActionType;
import state.State;

@AllArgsConstructor
public class NightState implements State {
    private static final String INFO = "Наступила ночь. Все граждане уснули.";
    private Game game;

    @Override
    public void nextGameLevel() {
        System.out.println(INFO);
        game.setState(game.getMafiaShootingState());
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
