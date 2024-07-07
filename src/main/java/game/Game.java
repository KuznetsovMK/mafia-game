package game;

import lombok.Data;
import state.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class Game {
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
    private boolean gameOver;
    private List<String> nightResults = new ArrayList<>();

    public Game() {
        this.preparationState = new PreparationState(this);
        this.definingRoleState = new DefiningRoleState(this);
        this.mafiaMeetingState = new MafiaMeetingState(this);
        this.dailyResultState = new DailyResultState(this);
        this.dailyMeetingState = new DailyMeetingState(this);
        this.lawsuitState = new LawsuitState(this);
        this.nightState = new NightState(this);
        this.mafiaShootingState = new MafiaShootingState(this);
        this.leaderMoveState = new LeaderMoveState(this);
        this.gameOverState = new GameOverState(this);

        state = preparationState;
    }

    public void nextGameLevel() {
        state.nextGameLevel();
    }
}
