package vote;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MafiaVote {
    private String mafiaName;
    private String targetName;
    private boolean confirm;
}
