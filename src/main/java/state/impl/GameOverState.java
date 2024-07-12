package state.impl;

import game.Game;
import lombok.AllArgsConstructor;
import role.RoleNameConst;
import state.State;

@AllArgsConstructor
public class GameOverState implements State {
    private static final String INFO = "Игра окончена!";
    private static final String WINNER_MAFIA = "МАФИЯ ПОБЕДИЛА!";
    private static final String WINNER_SETTLER = "ПОБЕДИЛИ МИРНЫЕ ЖИТЕЛИ!";
    private Game game;

    @Override
    public void nextGameLevel() {
        System.out.println(INFO);

        switch (game.getWinner()) {
            case MAFIA -> {
                System.out.println(WINNER_MAFIA);
                game.getPlayerByName().values().stream()
                        .filter(player -> RoleNameConst.MAFIA.equals(player.getRole()))
                        .forEach(player -> System.out.println(player.getName()));
            }
            case SETTLER -> {
                System.out.println(WINNER_SETTLER);
                game.getPlayerByName().values().stream()
                        .filter(player -> !RoleNameConst.MAFIA.equals(player.getRole()))
                        .forEach(player -> System.out.println(player.getName()));
            }
        }

        goNextLevel();
    }

    private void goNextLevel() {
//        game.setState(game.getPreparationState());
//        game.nextGameLevel();
    }
}
