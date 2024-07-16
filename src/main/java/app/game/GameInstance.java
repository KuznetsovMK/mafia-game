package app.game;

import app.command.Command;
import app.player.GameInstancePlayer;
import app.role.consts.ActionType;
import app.role.consts.WinnerType;
import app.role.impl.Settler;
import app.state.State;
import app.state.impl.DailyMeetingState;
import app.state.impl.GameOverState;
import app.state.impl.NightState;
import app.state.impl.PreparationState;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
public class GameInstance {
    private UUID id;
    private State preparationState;
    private State definingRoleState;
    private State mafiaMeetingState;
    private State dailyResultState;
    private State dailyMeetingState;
    private State lawsuitState;
    private State nightState;
    private State mafiaShootingState;
    private State leaderMoveState;
    private State gameOverState;
    private State state;

    private Map<String, GameInstancePlayer> playerByName = new HashMap<>();
    private Queue<Command> shootingQueue = new LinkedList<>();

    private Settler settler;
//    private Detective detective;
//    private Mafia mafia;

    private WinnerType winner;

    public GameInstance(UUID id) {
        this.id = id;
        preparationState = new PreparationState(this);
//        definingRoleState = new DefineRoleState(this);
//        mafiaMeetingState = new MafiaMeetingState(this);
//        dailyResultState = new DailyResultState(this);
        dailyMeetingState = new DailyMeetingState(this);
//        lawsuitState = new LawsuitState(this);
        nightState = new NightState(this);
//        mafiaShootingState = new MafiaShootingState(this);
//        leaderMoveState = new LeaderMoveState(this);
        gameOverState = new GameOverState(this);

        state = preparationState;

        settler = new Settler(this);
//        detective = new Detective(this);
//        mafia = new Mafia(this);
    }

    public void nextGameLevel() {
        state.nextGameLevel();
    }

    public void vote(String targetName, String initiatorName, ActionType actionType) {
        state.vote(targetName, initiatorName, actionType);
    }

    public GameInfoMessage info() {
        return state.info();
    }

    public void gameInfo() {
        System.out.println("Game state: %s".formatted(state.getClass().getSimpleName()));

        playerByName.values()
                .forEach(System.out::println);
    }
}
