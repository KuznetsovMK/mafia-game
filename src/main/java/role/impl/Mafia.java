package role.impl;

import lombok.Data;
import player.Player;
import role.RoleNameConst;
import role.Shooter;

import java.util.ArrayList;
import java.util.List;

@Data
public class Mafia implements Shooter {
    private List<Player> mafiaPlayers;
    private static final String INFO = "Прозвучал выстрел...";

    @Override
    public void shoot(Player target) {
        //todo команда выстрела. Изменение поля isAlive произойдёт
        // в момент выполнения команды
        target.setAlive(false);
        System.out.println(INFO);
    }

    public void addMafia(Player player) {
        mafiaPlayers.add(player);
        player.setRole(RoleNameConst.MAFIA);
        player.setAlive(true);
    }


    public Mafia(List<Player> players) {
        mafiaPlayers = new ArrayList<>();

        players.forEach(player -> {
            mafiaPlayers.add(player);
            player.setRole(RoleNameConst.MAFIA);
            player.setAlive(true);
        });
    }
}
