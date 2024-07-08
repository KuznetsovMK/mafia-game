package role.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import player.Player;
import role.Killer;
import role.Recognizer;
import role.Voter;
@Data
@AllArgsConstructor
public class Detective extends Role implements Voter, Killer, Recognizer {
    private Player player;

    @Override
    public void kill() {

    }

    @Override
    public void recognize() {

    }

    @Override
    public void vote(Player target) {

    }
}
