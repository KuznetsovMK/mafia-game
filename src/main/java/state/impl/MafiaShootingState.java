package state.impl;

import game.Game;
import lombok.AllArgsConstructor;
import lombok.Data;
import player.Player;
import role.ActionType;
import role.RoleNameConst;
import service.TargetService;
import state.State;
import vote.VotingForm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.*;

@Data
@AllArgsConstructor
public class MafiaShootingState implements State {
    private static final String INFO = "Просыпается мафия.";
    private final Game game;
    private Map<String, VotingForm> voteByMafiaName;
    private List<String> targets;

    @Override
    public void nextGameLevel() {
        System.out.println(INFO);
        voteByMafiaName = new HashMap<>();
        targets = getPossibleTargetNames();

        info();
    }

    @Override
    public void info() {
        var aliveMafia = game.getMafia().getMafiaPlayers().stream()
                .filter(Player::isAlive)
                .toList();

        aliveMafia.forEach(player -> player.sendMessage(generateInfo()));
    }

    @Override
    public void vote(String targetName, String initiatorName, ActionType actionType) {
        //добавить валидацию, что цель и стрелок существуют
        if (canVote(initiatorName))
            voteByMafiaName.put(initiatorName, new VotingForm(initiatorName, targetName, actionType));

        info();

        if (allConfirm()) {
            action();
            goNextGameLevel();
        }
    }

    private List<String> getPossibleTargetNames() {
        return game.getPlayerByName().values().stream()
                .filter(Player::isAlive)
                .filter(player -> !player.getRole().equals(RoleNameConst.MAFIA))
                .map(Player::getName)
                .toList();
    }

    private String generateInfo() {
        var votesByTargetName = voteByMafiaName.values().stream()
                .collect(groupingBy(VotingForm::getTargetName, mapping(identity(), toList())));

        return targets.stream()
                .collect(toMap(identity(), target -> votesByTargetName
                        .getOrDefault(target, List.of())))
                .toString();
    }

    private boolean canVote(String mafiaName) {
        var vote = voteByMafiaName.get(mafiaName);
        return vote == null || vote.getActionType() == null;
    }

    private boolean allConfirm() {
        return !voteByMafiaName.values().isEmpty() &&
                game.getMafia().getMafiaPlayers().stream().filter(Player::isAlive).count() ==
                        voteByMafiaName.values().size() &&
                voteByMafiaName.values().stream()
                        .allMatch(e -> e.getActionType() != null);
    }

    private void action() {
        var target = getTarget();
        var targetName = target.getTargetName();

        if (target.getActionType() == ActionType.SHOOT) {
            game.getMafia().shoot(game.getPlayerByName().get(targetName));
        }
    }

    private VotingForm getTarget() {
        return TargetService.defineTarget(voteByMafiaName);
    }

    private void goNextGameLevel() {
        game.setState(game.getLeaderMoveState());
        game.nextGameLevel();
    }

    public MafiaShootingState(Game game) {
        this.game = game;
    }
}
