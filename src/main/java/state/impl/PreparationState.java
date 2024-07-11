package state.impl;

import game.Game;
import lombok.AllArgsConstructor;
import player.Player;
import role.ActionType;
import state.State;

import static java.util.stream.Collectors.toMap;

@AllArgsConstructor
public class PreparationState implements State {
    private static final String INFO = "Ожидание готовности игроков.";
    private static final String ALL_READY = "Все игроки готовы.";
    private Game game;

    @Override
    public void nextGameLevel() {
        resetLastGamePlayerInfo();
        System.out.println(INFO);

        info();

        if (allPlayersReady()) {
            System.out.println(ALL_READY);
            goNextGameLevel();
        }
    }

    @Override
    public void info() {
        var alivePlayers = game.getPlayerByName().values().stream().toList();
        alivePlayers.forEach(player -> player.sendMessage(generateInfo()));
    }

    @Override
    public void vote(String targetName, String initiatorName, ActionType actionType) {
        //добавить валидацию, что игрок существует
        if (ActionType.READY.equals(actionType)) {
            game.getPlayerByName().get(initiatorName).setReady(true);
        }

        info();

        if (allPlayersReady()) {
            System.out.println(ALL_READY);
            goNextGameLevel();
        }
    }

    private String generateInfo() {
        var players = game.getPlayerByName().values().stream()
                .collect(toMap(
                        Player::getName,
                        Player::isReady));

        return players.toString();
    }

    private boolean allPlayersReady() {
        return game.getPlayerByName().values().stream()
                .allMatch(Player::isReady);
    }

    private void resetLastGamePlayerInfo() {
        game.getPlayerByName().values()
                .forEach(player -> {
                    player.setReady(false);
                    player.setAlive(false);
                    player.setRole(null);
                });
    }

    private void goNextGameLevel() {
        game.setState(game.getDefiningRoleState());
        game.nextGameLevel();
    }
}
