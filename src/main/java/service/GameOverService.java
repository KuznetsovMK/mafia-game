package service;

import lombok.experimental.UtilityClass;
import player.Player;
import role.RoleNameConst;
import role.WinnerType;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@UtilityClass
public class GameOverService {

    public WinnerType findWinner(List<Player> players) {
        var settlerCount = new AtomicInteger(0);
        var mafiaCount = new AtomicInteger(0);

        players.stream()
                .filter(Player::isAlive)
                .forEach(player -> {
                    if (RoleNameConst.MAFIA.equals(player.getRole())) {
                        mafiaCount.getAndIncrement();
                    } else {
                        settlerCount.getAndIncrement();
                    }
                });

        if (mafiaWin(mafiaCount.intValue(), settlerCount.intValue())) {
            return WinnerType.MAFIA;
        } else if (settleWin(mafiaCount.intValue())) {
            return WinnerType.SETTLER;
        } else {
            return null;
        }
    }

    private boolean mafiaWin(int mafiaCount, int settlerCount) {
        return mafiaCount == settlerCount ||
                mafiaCount > settlerCount;
    }

    private boolean settleWin(int mafiaCount) {
        return mafiaCount == 0;
    }
}
