package app.state.impl;

import app.game.GameInfoMessage;
import app.game.GameInstance;
import app.player.GameInstancePlayer;
import app.player.Player;
import app.player.PreparationStatePlayer;
import app.role.consts.ActionType;
import app.state.State;
import app.state.StateName;
import app.validator.SenderValidator;

import java.util.ArrayList;


public class PreparationState implements State {
    private static final String STATE_NAME = "Prepare";
    private static final String START_STATE_INFO = "Для начала игры нажмите кнопку - ГОТОВ";
    private static final String STATE_INFO = "Ожидание готовности всех игроков";
    private static final String END_STATE_INFO = "Все игроки готовы";
    private final GameInstance gameInstance;
    private final GameInfoMessage gameInfoMessage;

    @Override
    public String startLevelInfo() {
        return START_STATE_INFO;
    }

    @Override
    public void nextGameLevel() {
        goNextGameLevel();
    }

    private void goNextGameLevel() {
        if (allPlayersReady()) {
            gameInstance.setState(gameInstance.getDefiningRoleState());
            gameInstance.nextGameLevel();
        }
    }

    @Override
    public GameInfoMessage info() {
        return generateInfo();
    }

    private GameInfoMessage generateInfo() {
        var result = new ArrayList<Player>();

        gameInstance.getPlayerByName().values().stream()
                .map(player -> PreparationStatePlayer.builder()
                        .name(player.getName())
                        .ready(player.isReady())
                        .build()
                )
                .forEach(result::add);

        gameInfoMessage.setStateInfo(STATE_INFO);
        gameInfoMessage.setPreparationStatePlayers(result);

        return gameInfoMessage;
    }

    @Override
    public void vote(String target, String sender, ActionType type) {
        System.out.println("---- %s %s ----".formatted(sender, type));
        updateSender(sender, type);
        ifAllReadyUpdateStateInfo();
    }

    private void ifAllReadyUpdateStateInfo() {
        if (allPlayersReady())
            setStateInfo();
    }

    private void setStateInfo() {
        gameInfoMessage.setStateInfo(END_STATE_INFO);
    }

    private void updateSender(String sender, ActionType type) {
        validateSender(sender);
        setReady(sender, type);
    }

    private void validateSender(String sender) {
        SenderValidator.validate(gameInstance, sender);
    }

    private void setReady(String sender, ActionType type) {
        if (ActionType.READY.equals(type)) {
            gameInstance.getPlayerByName().get(sender).setReady(true);
        }
    }


    private boolean allPlayersReady() {
        return !gameInstance.getPlayerByName().isEmpty() &&
                gameInstance.getPlayerByName().values().stream()
                        .allMatch(GameInstancePlayer::isReady);
    }

    private GameInfoMessage initInfoMessage() {
        return GameInfoMessage.builder()
                .stateName(StateName.PREPARATION)
                .stateInfo(START_STATE_INFO)
                .build();
    }

    public PreparationState(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
        this.gameInfoMessage = initInfoMessage();
    }

    @Override
    public String toString() {
        return """
                stateName: %s
                players: %s
                """.formatted(
                gameInfoMessage.getStateInfo(),
                gameInfoMessage.getPreparationStatePlayers()
        );
    }
}
