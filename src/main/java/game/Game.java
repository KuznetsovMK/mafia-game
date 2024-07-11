package game;

import lombok.Data;
import player.Player;
import role.ActionType;
import role.impl.Detective;
import role.impl.Mafia;
import role.impl.Settler;
import state.State;
import state.impl.*;

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

    private Settler settler;
    private Detective detective;
    private Mafia mafia;

    public Game() {
        preparationState = new PreparationState(this);
        definingRoleState = new DefiningRoleState(this);
        mafiaMeetingState = new MafiaMeetingState(this);
        dailyResultState = new DailyResultState(this);
        dailyMeetingState = new DailyMeetingState(this);
        lawsuitState = new LawsuitState(this);
        nightState = new NightState(this);
        //ниличие той или иной стадии завист от наличие ролей для этой стадии
        //если роль мафии не была создана, то и стадия не должна создаваться
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
                .forEach(player -> System.out.println(player));
    }
}
