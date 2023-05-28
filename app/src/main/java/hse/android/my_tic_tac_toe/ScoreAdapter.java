package hse.android.my_tic_tac_toe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    class ScoreViewHolder extends RecyclerView.ViewHolder {
        public final LinearLayout scoreLayout;
        public final ScoreAdapter scoreAdapter;

        public ScoreViewHolder(@NonNull View itemView, ScoreAdapter adapter) {
            super(itemView);
            scoreLayout = itemView.findViewById(R.id.ScoreView);
            scoreAdapter = adapter;
        }
    }

    private final ScoreContainer scoreContainer = ScoreContainer.ScoreContainerProvider.provide();
    private LayoutInflater layoutInflater;

    public ScoreAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ScoreAdapter.ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.score_layout, parent, false);
        return new ScoreViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreAdapter.ScoreViewHolder holder, int position) {
        ScoreContainer.ScoreRecord record = scoreContainer.getResult(position);
        TextView name0 = holder.scoreLayout.findViewById(R.id.scoreName0);
        name0.setText(record.getName0());
        TextView score0 = holder.scoreLayout.findViewById(R.id.scorePoints0);
        score0.setText(record.getScore0().toString());
        TextView name1 = holder.scoreLayout.findViewById(R.id.scoreName1);
        name1.setText(record.getName1());
        TextView score1 = holder.scoreLayout.findViewById(R.id.scorePoints1);
        score1.setText(record.getScore1().toString());
    }

    @Override
    public int getItemCount() {
        return scoreContainer.size();
    }
}
