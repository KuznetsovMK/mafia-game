package game;

import command.Command;
import lombok.Data;
import player.Player;
import role.ActionType;
import role.WinnerType;
import role.impl.Detective;
import role.impl.Mafia;
import role.impl.Settler;
import state.State;
import state.impl.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@Data
public class Game {
    private State preparationState;
    private State definingRoleState;
    private State dailyResultState;
    private State dailyMeetingState;
    private State lawsuitState;
    private State nightState;
    private State mafiaShootingState;
    private State leaderMoveState;
    private State gameOverState;
    private State state;

    private Map<String, Player> playerByName = new HashMap<>();
    private Queue<Command> shootingQueue = new LinkedList<>();

    private Settler settler;
    private Detective detective;
    private Mafia mafia;

    private WinnerType winner;

    public Game() {
        preparationState = new PreparationState(this);
        definingRoleState = new DefineRoleState(this);
        dailyResultState = new DailyResultState(this);
        dailyMeetingState = new DailyMeetingState(this);
        lawsuitState = new LawsuitState(this);
        nightState = new NightState(this);
        mafiaShootingState = new MafiaShootingState(this);
        leaderMoveState = new LeaderMoveState(this);
        gameOverState = new GameOverState(this);

        state = preparationState;
    }

    public void nextGameLevel() {
        state.nextGameLevel();
    }

    public void vote(String targetName, String initiatorName, ActionType actionType) {
        state.vote(targetName, initiatorName, actionType);
    }

    public void info() {
        state.info();
    }

    public void gameInfo() {
        System.out.println("Game state: %s".formatted(state.getClass().getSimpleName()));

        playerByName.values()
                .forEach(System.out::println);
    }
}
