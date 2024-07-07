package state;

import game.Game;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PreparationState implements State {
    private Game game;

    @Override
    public void nextGameLevel() {
        System.out.println("Ожидание готовности игроков.");
        System.out.println("Все игроки готовы.");
        game.setState(game.getDefiningRoleState());
    }
}
