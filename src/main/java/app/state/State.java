package app.state;

import app.game.GameInfoMessage;
import app.role.consts.ActionType;

public interface State {

    default void nextGameLevel() {
        System.out.println("default method nextGameLevel");
    }

    default String startLevelInfo() {
        return null;
    }

    default String endLevelInfo() {
        return null;
    }

    default GameInfoMessage info() {
        return null;
    }

    default void vote(String target, String sender, ActionType type) {
        System.out.println("default method vote");
    }

    default void vote(String sender, ActionType type) {
        System.out.println("default method vote");
    }
}
