//package app.state.impl;
//
//import app.game.GameInstance;
//import app.player.Player;
//import app.role.consts.ActionType;
//import app.role.consts.RoleNameConst;
//import app.service.TargetService;
//import app.state.State;
//import app.vote.VotingForm;
//import lombok.Data;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static java.util.function.UnaryOperator.identity;
//import static java.util.stream.Collectors.*;
//
//@Data
//public class MafiaMeetingState implements State {
//    private static final String INFO = "Мафия знакомится.";
//    private static final String MEETING_CONFIRM = "Знакомство завершено";
//    private final GameInstance gameInstance;
//    private Map<String, VotingForm> voteByMafiaName;
//    private List<String> targets;
//
//    @Override
//    public void nextGameLevel() {
//        System.out.println(INFO);
//        //Уведосление для мафии с инструкцией
//
//        voteByMafiaName = new HashMap<>();
//        targets = getPossibleTargetNames();
//
//        info();
//    }
//
//    @Override
//    public void info() {
//        var aliveMafia = gameInstance.getMafia().getMafiaPlayers().stream()
//                .filter(Player::isAlive)
//                .toList();
//
//        aliveMafia.forEach(player -> player.sendMessage(generateInfo()));
//    }
//
//    @Override
//    public void vote(String target, String sender, ActionType type) {
//        //добавить валидацию, что цель и стрелок существуют
//        if (canVote(sender))
//            voteByMafiaName.put(sender, new VotingForm(sender, target, type));
//
//        info();
//
//        if (allConfirm()) {
//            action();
//            goNextGameLevel();
//        }
//    }
//
//    private List<String> getPossibleTargetNames() {
//        return gameInstance.getPlayerByName().values().stream()
//                .filter(Player::isAlive)
//                .filter(player -> !player.getRole().equals(RoleNameConst.MAFIA))
//                .map(Player::getName)
//                .toList();
//    }
//
//    private String generateInfo() {
//        var votesByTargetName = voteByMafiaName.values().stream()
//                .collect(groupingBy(VotingForm::getTargetName, mapping(identity(), toList())));
//
//        return targets.stream()
//                .collect(toMap(identity(), target -> votesByTargetName
//                        .getOrDefault(target, List.of())))
//                .toString();
//    }
//
//    private boolean canVote(String mafiaName) {
//        var vote = voteByMafiaName.get(mafiaName);
//        return vote == null || vote.getActionType() == null;
//    }
//
//    private boolean allConfirm() {
//        return !voteByMafiaName.values().isEmpty() &&
//                gameInstance.getMafia().getMafiaPlayers().stream().filter(Player::isAlive).count() ==
//                        voteByMafiaName.values().size() &&
//                voteByMafiaName.values().stream()
//                        .allMatch(e -> e.getActionType() != null);
//    }
//
//    private void action() {
//        var target = getTarget();
//        var targetName = target.getTargetName();
//
//        if (target.getActionType() == ActionType.SHOOT) {
//            System.out.println(MEETING_CONFIRM);
//        }
//    }
//
//    private VotingForm getTarget() {
//        return TargetService.defineTarget(voteByMafiaName);
//    }
//
//    private void goNextGameLevel() {
//        gameInstance.setState(gameInstance.getDailyMeetingState());
//        gameInstance.nextGameLevel();
//    }
//
//    public MafiaMeetingState(GameInstance gameInstance) {
//        this.gameInstance = gameInstance;
//    }
//}
