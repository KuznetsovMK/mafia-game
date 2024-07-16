package app.state.impl;

import app.game.GameInstance;
import app.state.State;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NightState implements State {
    private static final String INFO = "Наступила ночь. Все граждане уснули.";
    private GameInstance gameInstance;

    @Override
    public void nextGameLevel() {
        System.out.println(INFO);
        gameInstance.setState(gameInstance.getMafiaShootingState());
        gameInstance.nextGameLevel();
    }
}
