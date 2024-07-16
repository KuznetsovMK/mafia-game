package app.state.impl;

import app.game.GameInstance;
import app.role.consts.RoleNameConst;
import app.state.State;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameOverState implements State {
    private static final String INFO = "Игра окончена!";
    private static final String WINNER_MAFIA = "МАФИЯ ПОБЕДИЛА!";
    private static final String WINNER_SETTLER = "ПОБЕДИЛИ МИРНЫЕ ЖИТЕЛИ!";
    private GameInstance gameInstance;

    @Override
    public void nextGameLevel() {
        System.out.println(INFO);

        switch (gameInstance.getWinner()) {
            case MAFIA -> {
                System.out.println(WINNER_MAFIA);
                gameInstance.getPlayerByName().values().stream()
                        .filter(player -> RoleNameConst.MAFIA.equals(player.getRole()))
                        .forEach(player -> System.out.println(player.getName()));
            }
            case SETTLER -> {
                System.out.println(WINNER_SETTLER);
                gameInstance.getPlayerByName().values().stream()
                        .filter(player -> !RoleNameConst.MAFIA.equals(player.getRole()))
                        .forEach(player -> System.out.println(player.getName()));
            }
        }

        resetLastGamePlayerInfo();
        goNextLevel();
    }

    private void resetLastGamePlayerInfo() {
        gameInstance.getPlayerByName().values()
                .forEach(player -> {
                    player.setReady(false);
                    player.setAlive(false);
                    player.setRole(null);
                });
    }

    private void goNextLevel() {
        gameInstance.setState(gameInstance.getPreparationState());
        gameInstance.nextGameLevel();
    }
}
