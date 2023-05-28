package hse.android.my_tic_tac_toe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

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

        // Setup image buttons
        ImageButton imageButton0 = findViewById(R.id.Player0Avatar);
        imageButton0.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 3);
        });
        ImageButton imageButton1 = findViewById(R.id.Player1Avatar);
        imageButton1.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 4);
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            ImageButton imageButton;
            if (requestCode == 3) {
                imageButton = findViewById(R.id.Player0Avatar);
            } else {
                imageButton = findViewById(R.id.Player1Avatar);
            }
            imageButton.setImageURI(selectedImage);
        }
    }
}