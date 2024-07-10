package state.impl;

import game.Game;
import lombok.AllArgsConstructor;
import player.Player;
import role.ActionType;
import state.State;

import java.util.Comparator;
import java.util.Map;

@AllArgsConstructor
public class LawsuitState implements State {
    private Game game;

    @Override
    public void nextGameLevel() {
        System.out.println("Голосованием определите возможного преступника...");
        possibleSuspectNames();

        var suspectName = getSuspectName();

        if (suspectName != null) {
            System.out.println("Суд состоялся. Игрок %s выбывает.".formatted(suspectName));
            var suspect = game.getPlayerByName().get(suspectName);
            suspect.setAlive(false);
            game.getSuspect().clear();

            if (game.isGameOver()) {
                game.setState(game.getGameOverState());
            } else {
                game.setState(game.getNightState());
            }
        } else {
            System.out.println("Голосование не завершилось. Подозреваемый не определён.");
        }

        System.out.println("Технический переход на другой уровень");
    }

    @Override
    public void info() {

    }

    @Override
    public void vote(String targetName, String initiatorName, ActionType actionType) {

    }

    private void possibleSuspectNames() {
        game.getPlayerByName().values()
                .stream()
                .filter(Player::isAlive)
                .map(Player::getName)
                .forEach(System.out::println);
    }

    private String getSuspectName() {
        return game.getSuspect().entrySet()
                .stream()
                .max(Comparator.comparing(e -> e.getValue().size()))
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
