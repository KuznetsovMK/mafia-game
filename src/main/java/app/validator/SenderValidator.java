package app.validator;

import app.game.GameInstance;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SenderValidator {

    public void validate(GameInstance gameInstance, String name) {
        var player = gameInstance.getPlayerByName().get(name);
        if (player == null) {
            throw new IllegalArgumentException("Player with name %s does not exists".formatted(player.getName()));
        }
    }
}
