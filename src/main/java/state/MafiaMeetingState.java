package state;

import game.Game;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MafiaMeetingState implements State {
    private Game game;

    @Override
    public void nextGameLevel() {
        System.out.println("Начинается знакомство мафии друг с другом...");
        System.out.println("Мафия познакомилась.");
        game.setState(game.getDailyResultState());
    }
}
