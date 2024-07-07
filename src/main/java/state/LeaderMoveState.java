package state;

import game.Game;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LeaderMoveState implements State {
    private Game game;

    @Override
    public void nextGameLevel() {
        System.out.println("Просыпается лидер.");
        System.out.println("Лидер сделал свой ход.");
        game.setState(game.getDailyResultState());
    }
}
