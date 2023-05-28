package hse.android.my_tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FieldActivity extends AppCompatActivity {

    private final TicTacToe ticTacToe = new TicTacToe();
    private final Button[] buttons = new Button[9];
    private ScoreContainer scoreContainer;
    private PlayerContainer playerContainer;

    private void refreshField() {
        for (int i = 0; i < 9; ++i) {
            buttons[i].setText("");
        }
        unfreezeField();
    }

    private void freezeField() {
        for (int i = 0; i < 9; ++i) {
            buttons[i].setClickable(false);
        }
    }

    private void unfreezeField() {
        for (int i = 0; i < 9; ++i) {
            buttons[i].setClickable(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);

        scoreContainer = ScoreContainer.ScoreContainerProvider.provide();
        playerContainer = PlayerContainer.PlayerContainerProvider.provide();
        // Fetch nicknames & scores
        TextView[] playerText = new TextView[2];
        playerText[0] = findViewById(R.id.Player0Name);
        playerText[1] = findViewById(R.id.Player1Name);
        TextView[] playerScore = new TextView[2];
        playerScore[0] = findViewById(R.id.Player0Score);
        playerScore[1] = findViewById(R.id.Player1Score);
        playerText[0].setText(playerContainer.getPlayerName(0));
        playerText[1].setText(playerContainer.getPlayerName(1));
        playerScore[0].setText(playerContainer.getPlayerScore(0).toString());
        playerScore[1].setText(playerContainer.getPlayerScore(1).toString());

        // Setup refresh button
        Button refreshButton = findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(v -> {
            ticTacToe.refresh();
            refreshField();
            unfreezeField();
            refreshButton.setVisibility(View.INVISIBLE);
        });

        // Setup new game button
        Button newGameButton = findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(v -> {
            scoreContainer.addResult(playerContainer.getPlayer(0), playerContainer.getPlayer(1));
            playerContainer.refreshScore();
            playerText[0].setText(playerContainer.getPlayerName(0));
            playerText[1].setText(playerContainer.getPlayerName(1));
            playerScore[0].setText(playerContainer.getPlayerScore(0).toString());
            playerScore[1].setText(playerContainer.getPlayerScore(1).toString());
            ticTacToe.refresh();
            refreshField();
            refreshButton.setVisibility(View.INVISIBLE);
        });

        // Setup game buttons
        buttons[0] = findViewById(R.id.b0);
        buttons[1] = findViewById(R.id.b1);
        buttons[2] = findViewById(R.id.b2);
        buttons[3] = findViewById(R.id.b3);
        buttons[4] = findViewById(R.id.b4);
        buttons[5] = findViewById(R.id.b5);
        buttons[6] = findViewById(R.id.b6);
        buttons[7] = findViewById(R.id.b7);
        buttons[8] = findViewById(R.id.b8);
        for (int i = 0; i < 9; ++i) {
            int curInd = i;
            buttons[i].setOnClickListener(v -> {
                int curPlayer = ticTacToe.getCurrentPlayer();
                int res = ticTacToe.makeMove(curInd);
                if (res != 0) {
                    buttons[curInd].setText(Character.toString(ticTacToe.getSymbol(curPlayer)));
                }
                if (res == 2) { // Win
                    playerContainer.incPlayerScore(curPlayer);
                    playerScore[curPlayer].setText(playerContainer.getPlayerScore(curPlayer).toString());
                    refreshButton.setVisibility(View.VISIBLE);
                    freezeField();
                }
                if (res == 3) { // Draw
                    refreshButton.setVisibility(View.VISIBLE);
                    freezeField();
                }
            });
        }
    }
}