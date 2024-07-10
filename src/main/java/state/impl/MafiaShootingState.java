package state.impl;

import game.Game;
import lombok.Data;
import player.Player;
import role.ActionType;
import role.Information;
import role.RoleNameConst;
import state.State;
import vote.Voter;
import vote.VotingForm;

import java.util.*;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.*;

@Data
public class MafiaShootingState implements State, Voter, Information {
    private static final String INFO = "Просыпается мафия.";
    private final Game game;
    private Map<String, VotingForm> voteByMafiaName;
    private List<String> targets;

    @Override
    public void nextGameLevel() {
        if (someOneAlive()) {
            System.out.println(INFO);
            voteByMafiaName = new HashMap<>();
            targets = getPossibleTargetNames();

            info();

        } else {
            goNextGameLevel();
        }
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

    private boolean someOneAlive() {
        return game.getMafia().getMafiaPlayers().stream()
                .anyMatch(Player::isAlive);
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
        var targetList = new ArrayList<>(voteByMafiaName.values().stream()
                .toList());
        if (targetList.size() > 1) {
            Collections.shuffle(targetList);
            Collections.shuffle(targetList);
        }

        return targetList.get(0);
    }

    private void goNextGameLevel() {
        game.setState(game.getLeaderMoveState());
        game.nextGameLevel();
    }

    public MafiaShootingState(Game game) {
        this.game = game;
    }
}
