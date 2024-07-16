//package app.state.impl;
//
//import app.game.GameInstance;
//import app.player.Player;
//import app.player.ShortPlayer;
//import app.role.consts.ActionType;
//import app.role.consts.RoleNameConst;
//import app.service.TargetService;
//import app.state.State;
//import app.vote.VotingForm;
//import lombok.Data;
//
//import java.util.*;
//
//import static java.util.function.UnaryOperator.identity;
//import static java.util.stream.Collectors.*;
//
//@Data
//public class LeaderMoveState implements State {
//    private static final String INFO = "Просыпается лидер.";
//    private final GameInstance gameInstance;
//    private Map<String, VotingForm> voteByDetectiveName;
//    private List<ShortPlayer> targets;
//
//    @Override
//    public void nextGameLevel() {
//        if (gameInstance.getDetective().getDetectivePlayer().isAlive()) {
//            System.out.println(INFO);
//            //Личное Уведосление с инструкцией
//
//            voteByDetectiveName = new HashMap<>();
//            targets = getPossibleTargetNames();
//
//            info();
//
//        } else {
//            goNextGameLevel();
//        }
//    }
//
//    @Override
//    public void info() {
//        gameInstance.getDetective().getDetectivePlayer().sendMessage(generateInfo());
//    }
//
//    @Override
//    public void vote(String target, String sender, ActionType type) {
//        //добавить валидацию, что цель и стрелок существуют
//        if (canVote(sender))
//            voteByDetectiveName.put(sender, new VotingForm(sender, target, type));
//
//        info();
//
//        if (allConfirm()) {
//            action();
//            goNextGameLevel();
//        }
//    }
//
//    private List<ShortPlayer> getPossibleTargetNames() {
//        return gameInstance.getPlayerByName().values().stream()
//                .filter(Player::isAlive)
//                .filter(player -> !player.getRole().equals(RoleNameConst.DETECTIVE))
//                .map(player -> ShortPlayer.builder().name(player.getName()).build())
//                .toList();
//    }
//
//    private String generateInfo() {
//        var votesByTargetName = voteByDetectiveName.values().stream()
//                .collect(groupingBy(VotingForm::getTargetName, mapping(identity(), toList())));
//
//        return targets.stream()
//                .collect(toMap(target -> {
//                            var targetPlayerRole = gameInstance.getDetective().getTargetRoleByName().get(target.getName());
//                            target.setRole(targetPlayerRole);
//
//                            return target;
//                        },
//                        target -> votesByTargetName
//                                .getOrDefault(target.getName(), List.of())))
//                .toString();
//    }
//
//    private boolean canVote(String detectiveName) {
//        var vote = voteByDetectiveName.get(detectiveName);
//        return vote == null || vote.getActionType() == null;
//    }
//
//    private boolean allConfirm() {
//        return !voteByDetectiveName.values().isEmpty() &&
//                voteByDetectiveName.values().stream()
//                        .allMatch(e -> e.getActionType() != null);
//    }
//
//    private void action() {
//        var target = getTarget();
//        var targetName = target.getTargetName();
//
//        switch (target.getActionType()) {
//            case LOOK -> {
//                gameInstance.getDetective().look(targetName);
//                info();
//            }
//            case SHOOT -> gameInstance.getDetective().shoot(gameInstance.getPlayerByName().get(targetName));
//        }
//    }
//
//    private VotingForm getTarget() {
//        return TargetService.defineTarget(voteByDetectiveName);
//    }
//
//    private void goNextGameLevel() {
//        gameInstance.setState(gameInstance.getDailyResultState());
//        gameInstance.nextGameLevel();
//    }
//
//    public LeaderMoveState(GameInstance gameInstance) {
//        this.gameInstance = gameInstance;
//    }
//}
