package state;

import game.Game;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameOverState implements State {
    private Game game;

    @Override
    public void nextGameLevel() {
        System.out.println("Игра окончена.");
        game.setState(game.getPreparationState());
    }
}
