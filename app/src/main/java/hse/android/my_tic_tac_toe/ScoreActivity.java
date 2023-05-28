package hse.android.my_tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.concurrent.RecursiveAction;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        RecyclerView scoreRecycler = findViewById(R.id.scoreRecycler);
        ScoreAdapter scoreAdapter = new ScoreAdapter(this);
        scoreRecycler.setAdapter(scoreAdapter);
        scoreRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}