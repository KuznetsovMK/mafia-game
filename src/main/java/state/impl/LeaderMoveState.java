package state.impl;

import game.Game;
import player.Player;
import player.ShortPlayer;
import role.ActionType;
import role.Information;
import role.RoleNameConst;
import role.impl.Detective;
import state.State;
import vote.Voter;
import vote.VotingForm;

import java.util.*;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.*;

public class LeaderMoveState implements State, Voter, Information {
    private static final String INFO = "Просыпается лидер.";
    private final Game game;
    private final Detective detective;
    private Map<String, VotingForm> voteByDetectiveName;
    private List<ShortPlayer> targets;

    @Override
    public void nextGameLevel() {
        System.out.println(INFO);
        voteByDetectiveName = new HashMap<>();
        targets = getPossibleTargetNames();

        info();
    }

    @Override
    public void info() {
        detective.getDetectivePlayer().sendMessage(generateInfo());
    }

    @Override
    public void vote(String targetName, String initiatorName, ActionType actionType) {
        if (canVote(initiatorName))
            voteByDetectiveName.put(initiatorName, new VotingForm(initiatorName, targetName, actionType));

        if (allConfirm()) {
            action();
            goNextGameLevel();
        }
    }

    private List<ShortPlayer> getPossibleTargetNames() {
        return game.getPlayerByName().values().stream()
                .filter(Player::isAlive)
                .filter(player -> !player.getRole().equals(RoleNameConst.DETECTIVE))
                .map(player -> ShortPlayer.builder().name(player.getName()).build())
                .toList();
    }

    private String generateInfo() {
        var votesByTargetName = voteByDetectiveName.values().stream()
                .collect(groupingBy(VotingForm::getTargetName, mapping(identity(), toList())));

        return targets.stream()
                .collect(toMap(target -> {
                            var targetPlayerRole = detective.getTargetRoleByName().get(target.getName());
                            target.setRole(targetPlayerRole);

                            return target;
                        },
                        target -> votesByTargetName
                                .getOrDefault(target.getName(), List.of())))
                .toString();
    }

    private boolean canVote(String detectiveName) {
        var vote = voteByDetectiveName.get(detectiveName);
        return vote.getActionType() == null;
    }

    private boolean allConfirm() {
        return voteByDetectiveName.values().stream()
                .allMatch(e -> e.getActionType() != null);
    }

    private void action() {
        var target = getTarget();
        var targetName = target.getTargetName();

        switch (target.getActionType()) {
            case LOOK -> detective.look(targetName);
            case SHOOT -> detective.shoot(game.getPlayerByName().get(targetName));
        }
    }

    private VotingForm getTarget() {
        var targetList = new ArrayList<>(voteByDetectiveName.values().stream()
                .toList());
        if (targetList.size() > 1) {
            Collections.shuffle(targetList);
            Collections.shuffle(targetList);
        }

        return targetList.get(0);
    }

    private void goNextGameLevel() {
        game.setState(game.getLeaderMoveState());
    }

    public LeaderMoveState(Game game, Detective detective) {
        this.game = game;
        this.detective = detective;
    }
}
