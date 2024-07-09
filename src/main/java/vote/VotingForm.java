package vote;

import lombok.AllArgsConstructor;
import lombok.Data;
import role.ActionType;

@Data
@AllArgsConstructor
public class VotingForm {
    private String initiatorName;
    private String targetName;
    private ActionType actionType;
}
