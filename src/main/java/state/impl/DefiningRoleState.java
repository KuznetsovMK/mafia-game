package state.impl;

import game.Game;
import lombok.AllArgsConstructor;
import role.ActionType;
import state.State;

@AllArgsConstructor
public class DefiningRoleState implements State {
    private Game game;

    @Override
    public void nextGameLevel() {
        System.out.println("Назначение ролей...");

        if (roleIsDefining()) {
            System.out.println("Всем игрокам назначены роли. Игра начинается!");
            game.setState(game.getMafiaMeetingState());
        } else {
            System.out.println("Роли еще не назначены всем игрокам.");
        }
    }

    @Override
    public void info() {

    }

    @Override
    public void vote(String targetName, String initiatorName, ActionType actionType) {

    }

    private boolean roleIsDefining() {
        return game.getPlayerByName().values().stream()
                .allMatch(e -> e.getRole() != null);
    }
}
