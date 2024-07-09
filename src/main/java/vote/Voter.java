package vote;

import role.ActionType;

public interface Voter {

    void vote(String targetName, String initiatorName, ActionType actionType);
}
