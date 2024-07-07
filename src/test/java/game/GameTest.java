package game;

import org.junit.jupiter.api.Test;


class GameTest {

    @Test
    void nextGameLevelTest() {
        var game = new Game();

        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();
        game.setGameOver(true);
        game.nextGameLevel();
        game.nextGameLevel();
    }
}