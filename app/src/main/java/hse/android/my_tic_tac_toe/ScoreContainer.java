package hse.android.my_tic_tac_toe;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;


/**
 * Singleton
 */
public class ScoreContainer {
    private final List<AbstractMap.SimpleEntry<PlayerContainer.Player, PlayerContainer.Player>> score = new ArrayList<>();

    public void addResult(PlayerContainer.Player player1, PlayerContainer.Player player2) {
        score.add(new AbstractMap.SimpleEntry<>(player1, player2));
    }

    public static class ScoreContainerProvider {
        private static ScoreContainer scoreContainer = null;

        public static ScoreContainer provide() {
            if (scoreContainer == null) {
                scoreContainer = new ScoreContainer();
            }
            return scoreContainer;
        }

    }

}
