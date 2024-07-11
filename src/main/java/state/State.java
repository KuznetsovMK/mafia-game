package state;

import role.ActionType;

public interface State {

    void nextGameLevel();

    default void info() {
        System.out.println("default method info");
    }

    default void vote(String targetName, String initiatorName, ActionType actionType) {
        System.out.println("default method vote");
    }
}
