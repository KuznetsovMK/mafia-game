package app.service;

import lombok.experimental.UtilityClass;
import app.vote.VotingForm;

import java.util.*;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.*;


@UtilityClass
public class TargetService {
    public VotingForm defineTarget(Map<String, VotingForm> voteFormByInitiatorName) {
        var votesByTargetName = voteFormByInitiatorName.values().stream()
                .collect(groupingBy(VotingForm::getTargetName, mapping(identity(), toList())));

        int maxVotes = votesByTargetName.values()
                .stream()
                .map(List::size)
                .max(Comparator.comparing(e -> e))
                .orElse(0);

        var result = new ArrayList<>(votesByTargetName.values().stream()
                .filter(e -> e.size() == maxVotes)
                .flatMap(Collection::stream)
                .toList());

        if (result.size() > 1) {
            Collections.shuffle(result);
            Collections.shuffle(result);
        }

        return result.get(0);
    }
}
