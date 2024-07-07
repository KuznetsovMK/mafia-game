package state;

import game.Game;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NightState implements State {
    private Game game;

    @Override
    public void nextGameLevel() {
        System.out.println("Наступила ночь. Все граждане уснули.");
        game.setState(game.getMafiaShootingState());
    }
}
