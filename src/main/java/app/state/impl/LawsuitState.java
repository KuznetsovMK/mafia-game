//package app.state.impl;
//
//import app.game.GameInstance;
//import app.player.Player;
//import app.role.consts.ActionType;
//import app.role.consts.WinnerType;
//import app.service.GameOverService;
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
//public class LawsuitState implements State {
//    private static final String INFO = "Голосованием определите возможного преступника...";
//    private final GameInstance gameInstance;
//    private Map<String, VotingForm> voteFormByInitiatorName;
//    private List<String> targets;
//
//    @Override
//    public void nextGameLevel() {
//        System.out.println(INFO);
//        voteFormByInitiatorName = new HashMap<>();
//        targets = getPossibleTargetNames();
//
//        info();
//    }
//
//    @Override
//    public void info() {
//        //мафия должна получать информацию кто мафия имя/роль
//        //детектив должен получать имя/роль для тех кого он успел изучить
//        var alivePlayers = gameInstance.getPlayerByName().values().stream()
//                .filter(Player::isAlive)
//                .toList();
//
//        alivePlayers.forEach(player -> player.sendMessage(generateInfo()));
//    }
//
//    @Override
//    public void vote(String target, String sender, ActionType type) {
//        //добавить валидацию, что цель и стрелок существуют и живы
//        if (canVote(sender))
//            voteFormByInitiatorName.put(sender, new VotingForm(sender, target, type));
//
//        info();
//        execute();
//    }
//
//    private List<String> getPossibleTargetNames() {
//        return gameInstance.getPlayerByName().values().stream()
//                .filter(Player::isAlive)
//                .map(Player::getName)
//                .toList();
//    }
//
//    private String generateInfo() {
//        var votesByTargetName = voteFormByInitiatorName.values().stream()
//                .collect(groupingBy(VotingForm::getTargetName, mapping(identity(), toList())));
//
//        return targets.stream()
//                .collect(toMap(identity(), target -> votesByTargetName
//                        .getOrDefault(target, List.of())))
//                .toString();
//    }
//
//    private boolean canVote(String initiatorName) {
//        var vote = voteFormByInitiatorName.get(initiatorName);
//        return vote == null || vote.getActionType() == null;
//    }
//
//    private void execute() {
//        if (allConfirm()) {
//            action();
//            goNext();
//        }
//    }
//
//    private boolean allConfirm() {
//        return !voteFormByInitiatorName.values().isEmpty() &&
//                gameInstance.getPlayerByName().values().stream().filter(Player::isAlive).count() ==
//                        voteFormByInitiatorName.values().size() &&
//                voteFormByInitiatorName.values().stream()
//                        .allMatch(e -> e.getActionType() != null);
//    }
//
//    private void action() {
//        var votingForm = defineTarget();
//
//        if (votingForm.getActionType() == ActionType.JUDGE) {
//            gameInstance.getSettler().judge(votingForm.getTargetName());
//        }
//    }
//
//    private VotingForm defineTarget() {
//        return TargetService.defineTarget(voteFormByInitiatorName);
//    }
//
//    private void goNext() {
//        var winner = GameOverService
//                .findWinner(gameInstance.getPlayerByName().values().stream().toList());
//
//        if (winner != null) {
//            gameOverLevel(winner);
//
//        } else {
//            goNextGameLevel();
//        }
//    }
//
//    private void gameOverLevel(WinnerType winner) {
//        gameInstance.setWinner(winner);
//        gameInstance.setState(gameInstance.getGameOverState());
//        gameInstance.nextGameLevel();
//    }
//
//    private void goNextGameLevel() {
//        gameInstance.setState(gameInstance.getNightState());
//        gameInstance.nextGameLevel();
//    }
//
//    public LawsuitState(GameInstance gameInstance) {
//        this.gameInstance = gameInstance;
//    }
//}
