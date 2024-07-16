package app.vote;

import lombok.AllArgsConstructor;
import lombok.Data;
import app.role.consts.ActionType;

@Data
@AllArgsConstructor
public class VotingForm {
    private String initiatorName;
    private String targetName;
    private ActionType actionType;
}
