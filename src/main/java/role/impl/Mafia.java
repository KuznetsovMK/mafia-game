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
    //обмен клиента и сервера (голосование за выбор жертвы)
    //действие завершения хода фиксирует выбор игрока
    //после того как все сделаю выбор, определить одну жерву и вызвать команду

    @Override
    public void shoot(Player target) {
        //todo команда выстрела. Изменение поля isAlive произойдёт
        // в момент выполнения команды
        System.out.println("Прозвучал выстрел...");
    }

    public void addMafia(Player player) {
        mafiaPlayers.add(player);
        player.setRole(RoleNameConst.MAFIA);
        player.setAlive(true);
    }



    public Mafia() {
        this.mafiaPlayers = new ArrayList<>();
    }
}
