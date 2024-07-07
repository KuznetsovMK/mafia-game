package state;

import game.Game;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LawsuitState implements State {
    private Game game;

    @Override
    public void nextGameLevel() {
        System.out.println("Начинается голосование...");
        System.out.println("Суд на подозреваемыми состоялся. Игрок выбывает.");
        if (game.isGameOver()) {
            game.setState(game.getGameOverState());
        } else {
            game.setState(game.getNightState());
        }
    }
}
