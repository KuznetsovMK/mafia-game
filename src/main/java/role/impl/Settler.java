package role.impl;

import lombok.Data;
import player.Player;
import role.RoleNameConst;

import java.util.ArrayList;
import java.util.List;

@Data
public class Settler {
    private List<Player> settlerPlayers;

    public void addSettler(Player player) {
        settlerPlayers.add(player);
        player.setRole(RoleNameConst.SETTLER);
        player.setAlive(true);
    }

    public Settler() {
        this.settlerPlayers = new ArrayList<>();
    }
}
