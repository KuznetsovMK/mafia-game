package state.impl;

import game.Game;
import lombok.AllArgsConstructor;
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
}
