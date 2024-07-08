package game;

import lombok.Data;
import player.Player;
import state.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//    private List<Player> players = new ArrayList<>();
    private Map<String, Player> playerByName = new HashMap<>();
    private Map<String, List<String>> suspect = new HashMap<>();

    public Game() {
        preparationState = new PreparationState(this);
        definingRoleState = new DefiningRoleState(this);
        mafiaMeetingState = new MafiaMeetingState(this);
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
}
