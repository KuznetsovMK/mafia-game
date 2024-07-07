package state;

import game.Game;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefiningRoleState implements State {
    private Game game;

    @Override
    public void nextGameLevel() {
        System.out.println("Назначение ролей...");
        System.out.println("Всем игрокам назначены роли. Игра начинается!");
        game.setState(game.getMafiaMeetingState());
    }
}
