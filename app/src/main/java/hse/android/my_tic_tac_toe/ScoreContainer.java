package hse.android.my_tic_tac_toe;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;


/**
 * Singleton
 */
public class ScoreContainer {
    private final List<ScoreRecord> score = new ArrayList<>();

    public class ScoreRecord {
        private String name0, name1;
        private int score0, score1;

        ScoreRecord(String name0, String name1, int score0, int score1) {
            this.name0 = name0;
            this.name1 = name1;
            this.score0 = score0;
            this.score1 = score1;
        }

        public String getName0() {
            return name0;
        }

        public String getName1() {
            return name1;
        }

        public Integer getScore0() {
            return score0;
        }

        public Integer getScore1() {
            return score1;
        }
    }

    public void addResult(PlayerContainer.Player player0, PlayerContainer.Player player1) {
        score.add(new ScoreRecord(player0.getName(), player1.getName(), player0.getScore(), player1.getScore()));
    }

    public ScoreRecord getResult(int position) {
        return score.get(position);
    }

    public int size() {
        return score.size();
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
