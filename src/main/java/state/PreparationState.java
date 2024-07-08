package state;

import game.Game;
import lombok.AllArgsConstructor;
import player.Player;

import java.util.List;

@AllArgsConstructor
public class PreparationState implements State {
    private Game game;

    @Override
    public void nextGameLevel() {
        System.out.println("Ожидание готовности игроков.");

        if (allPlayersReady()) {
            System.out.println("Все игроки готовы.");
            game.setState(game.getDefiningRoleState());
        } else {
            System.out.println("Следующие игроки еще не готовы: %s".formatted(getUnreadyPlayerNames()));
        }

    }

    private boolean allPlayersReady() {
        return game.getPlayerByName().values().stream()
                .allMatch(Player::isReady);
    }

    private List<String> getUnreadyPlayerNames() {
        return game.getPlayerByName().values().stream()
                .filter(e -> !e.isReady())
                .map(Player::getName)
                .toList();
    }
}
