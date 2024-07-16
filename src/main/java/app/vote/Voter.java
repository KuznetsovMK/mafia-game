package app.vote;

import app.role.consts.ActionType;

public interface Voter {

    void vote(String targetName, String initiatorName, ActionType actionType);
}
