package state;

import game.Game;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DailyMeetingState implements State {
    private final Game game;

    @Override
    public void nextGameLevel() {
        System.out.println("Местные жители встречаются, чтобы обсудить последние новости и определить двох подозреваемых...");

        System.out.println("Подозреваемые определены.");
        game.setState(game.getLawsuitState());
    }
}
