package hse.android.my_tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PlayerContainer playerContainer = PlayerContainer.PlayerContainerProvider.provide();

        // Setup start button
        Button startButton = findViewById(R.id.PlayButton);
        startButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, FieldActivity.class)));

        // Setup score button
        Button scoreButton = findViewById(R.id.ScoreButton);
        scoreButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ScoreActivity.class)));

        // Setup confirm buttons
        Button confirmButton0 = findViewById(R.id.Player0ConfirmButton);
        Button confirmButton1 = findViewById(R.id.Player1ConfirmButton);
        EditText player0Name = findViewById(R.id.Player0EnterName);
        confirmButton0.setOnClickListener(v -> {
            playerContainer.updatePlayer(0, player0Name.getText().toString());
        });
        EditText player1Name = findViewById(R.id.Player1EnterName);
        confirmButton1.setOnClickListener(v -> {
            playerContainer.updatePlayer(1, player1Name.getText().toString());
        });

    }
}