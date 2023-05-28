package hse.android.my_tic_tac_toe;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class PlayerContainer {

    public class Player {
        private String name;
        private int score;

        public String getName() {
            return name;
        }

        public Integer getScore() {
            return score;
        }
    }

    private final Player[] players;

    PlayerContainer() {
        players = new Player[2];
        players[0] = new Player();
        updatePlayer(0, "Player 0");
        players[1] = new Player();
        updatePlayer(1, "Player 1");
    }

    public static class PlayerContainerProvider {
        private static PlayerContainer playerContainer = null;

        public static PlayerContainer provide() {
            if (playerContainer == null) playerContainer = new PlayerContainer();
            return playerContainer;
        }
    }

    public void updatePlayer(int num, String name) {
        players[num].name = name;
        players[num].score = 0;
    }

    public void incPlayerScore(int num) {
        players[num].score++;
    }

    public String getPlayerName(int num) {
        return players[num].name;
    }

    public Integer getPlayerScore(int num) {
        return players[num].score;
    }

    public Player getPlayer(int num) {
        return players[num];
    }

    public void refreshScore() {
        players[0].score = 0;
        players[1].score = 0;
    }

}
