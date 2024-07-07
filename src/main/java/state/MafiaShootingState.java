package state;

import game.Game;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MafiaShootingState implements State {
    private Game game;

    @Override
    public void nextGameLevel() {
        System.out.println("Просыпается мафия...");
        System.out.println("Мафия выбрала жертву. Прозвучал выстрел...");
        game.setState(game.getLeaderMoveState());
    }
}
