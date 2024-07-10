package state;

import role.ActionType;

public interface State {

    void nextGameLevel();

    void info();

    void vote(String targetName, String initiatorName, ActionType actionType);
}
