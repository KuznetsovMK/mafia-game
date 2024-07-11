package state.impl;

import game.Game;
import lombok.Data;
import player.Player;
import role.ActionType;
import service.TargetService;
import state.State;
import vote.VotingForm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.*;

@Data
public class LawsuitState implements State {
    private static final String INFO = "Голосованием определите возможного преступника...";
    private final Game game;
    private Map<String, VotingForm> voteFormByInitiatorName;
    private List<String> targets;

    @Override
    public void nextGameLevel() {
        if (someOneAlive()) {
            System.out.println(INFO);
            voteFormByInitiatorName = new HashMap<>();
            targets = getPossibleTargetNames();

            info();

        } else {
            goNextGameLevel();
        }
    }

    @Override
    public void info() {
        var alivePlayers = game.getPlayerByName().values().stream()
                .filter(Player::isAlive)
                .toList();

        alivePlayers.forEach(player -> player.sendMessage(generateInfo()));
    }

    @Override
    public void vote(String targetName, String initiatorName, ActionType actionType) {
        //добавить валидацию, что цель и стрелок существуют и живы
        if (canVote(initiatorName))
            voteFormByInitiatorName.put(initiatorName, new VotingForm(initiatorName, targetName, actionType));

        info();

        if (allConfirm()) {
            action();
            goNextGameLevel();
        }
    }

    private boolean someOneAlive() {
        return game.getPlayerByName().values().stream()
                .anyMatch(Player::isAlive);
    }

    private List<String> getPossibleTargetNames() {
        return game.getPlayerByName().values().stream()
                .filter(Player::isAlive)
                .map(Player::getName)
                .toList();
    }

    private String generateInfo() {
        var votesByTargetName = voteFormByInitiatorName.values().stream()
                .collect(groupingBy(VotingForm::getTargetName, mapping(identity(), toList())));

        return targets.stream()
                .collect(toMap(identity(), target -> votesByTargetName
                        .getOrDefault(target, List.of())))
                .toString();
    }

    private boolean canVote(String initiatorName) {
        var vote = voteFormByInitiatorName.get(initiatorName);
        return vote == null || vote.getActionType() == null;
    }

    private boolean allConfirm() {
        return !voteFormByInitiatorName.values().isEmpty() &&
                game.getPlayerByName().values().stream().filter(Player::isAlive).count() ==
                        voteFormByInitiatorName.values().size() &&
                voteFormByInitiatorName.values().stream()
                        .allMatch(e -> e.getActionType() != null);
    }

    private void action() {
        var votingForm = defineTarget();

        if (votingForm.getActionType() == ActionType.JUDGE) {
            game.getSettler().judge(votingForm.getTargetName());
        }
    }

    private VotingForm defineTarget() {
        return TargetService.defineTarget(voteFormByInitiatorName);
    }

    private void goNextGameLevel() {
        game.setState(game.getNightState());
        game.nextGameLevel();
    }

    public LawsuitState(Game game) {
        this.game = game;
    }
}
