package state.impl;

import game.Game;
import lombok.Data;
import player.Player;
import player.ShortPlayer;
import role.ActionType;
import role.RoleNameConst;
import service.TargetService;
import state.State;
import vote.VotingForm;

import java.util.*;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.*;

@Data
public class LeaderMoveState implements State {
    private static final String INFO = "Просыпается лидер.";
    private final Game game;
    private Map<String, VotingForm> voteByDetectiveName;
    private List<ShortPlayer> targets;

    @Override
    public void nextGameLevel() {
        if (game.getDetective().getDetectivePlayer().isAlive()) {
            System.out.println(INFO);
            voteByDetectiveName = new HashMap<>();
            targets = getPossibleTargetNames();

            info();

        } else {
            goNextGameLevel();
        }
    }

    @Override
    public void info() {
        game.getDetective().getDetectivePlayer().sendMessage(generateInfo());
    }

    @Override
    public void vote(String targetName, String initiatorName, ActionType actionType) {
        //добавить валидацию, что цель и стрелок существуют
        if (canVote(initiatorName))
            voteByDetectiveName.put(initiatorName, new VotingForm(initiatorName, targetName, actionType));

        info();

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
                            var targetPlayerRole = game.getDetective().getTargetRoleByName().get(target.getName());
                            target.setRole(targetPlayerRole);

                            return target;
                        },
                        target -> votesByTargetName
                                .getOrDefault(target.getName(), List.of())))
                .toString();
    }

    private boolean canVote(String detectiveName) {
        var vote = voteByDetectiveName.get(detectiveName);
        return vote == null || vote.getActionType() == null;
    }

    private boolean allConfirm() {
        return !voteByDetectiveName.values().isEmpty() &&
                voteByDetectiveName.values().stream()
                        .allMatch(e -> e.getActionType() != null);
    }

    private void action() {
        var target = getTarget();
        var targetName = target.getTargetName();

        switch (target.getActionType()) {
            case LOOK -> {
                game.getDetective().look(targetName);
                info();
            }
            case SHOOT -> game.getDetective().shoot(game.getPlayerByName().get(targetName));
        }
    }

    private VotingForm getTarget() {
        return TargetService.defineTarget(voteByDetectiveName);
    }

    private void goNextGameLevel() {
        game.setState(game.getDailyResultState());
        game.nextGameLevel();
    }

    public LeaderMoveState(Game game) {
        this.game = game;
    }
}
