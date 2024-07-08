package state;

import game.Game;
import lombok.AllArgsConstructor;
import player.Player;

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
