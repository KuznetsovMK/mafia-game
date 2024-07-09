package state;

import game.Game;
import lombok.Data;
import player.Player;
import role.RoleNameConst;
import role.impl.Mafia;
import vote.MafiaVote;

import java.util.*;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.*;

@Data
public class MafiaShootingState implements State {
    private final Game game;
    private final Mafia mafia;
    private Map<String, MafiaVote> voteByMafiaName;
    private List<String> targets;

    @Override
    public void nextGameLevel() {
        System.out.println("Просыпается мафия.");
        voteByMafiaName = new HashMap<>();
        targets = getPossibleTargetNames();

        info();
    }

    public void info() {
        var aliveMafia = mafia.getMafiaPlayers().stream()
                .filter(Player::isAlive)
                .toList();

        aliveMafia.forEach(e -> e.sendMessage(generateInfo()));
    }

    public void vote(String targetName, String mafiaName, boolean confirm) {
        if (canVote(mafiaName))
            voteByMafiaName.put(mafiaName, new MafiaVote(mafiaName, targetName, confirm));

        if (allConfirm()) {
            mafia.shoot(getTarget());
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
                .collect(groupingBy(MafiaVote::getTargetName, mapping(identity(), toList())));

        return targets.stream()
                .collect(toMap(identity(), target -> votesByTargetName
                        .getOrDefault(target, List.of())))
                .toString();
    }

    private boolean canVote(String mafiaName) {
        var vote = voteByMafiaName.get(mafiaName);
        return !vote.isConfirm();
    }

    private boolean allConfirm() {
        return voteByMafiaName.values().stream()
                .allMatch(MafiaVote::isConfirm);
    }

    private Player getTarget() {
        var targetNames = new ArrayList<>(voteByMafiaName.values().stream()
                .map(MafiaVote::getTargetName)
                .toList());
        Collections.shuffle(targetNames);

        var targetName = targetNames.get(0);
        return game.getPlayerByName().get(targetName);
    }

    private void goNextGameLevel() {
        game.setState(game.getLeaderMoveState());
    }

    public MafiaShootingState(Game game, Mafia mafia) {
        this.game = game;
        this.mafia = mafia;
    }
}
