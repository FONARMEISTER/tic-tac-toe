package hse.android.my_tic_tac_toe;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class TicTacToe implements Parcelable {

    public static class TicTacToeProvider {
        private static TicTacToe ticTacToe;

        public static TicTacToe provide() {
            if (ticTacToe == null) ticTacToe = new TicTacToe();
            return ticTacToe;
        }
    }

    private final char[] cells = new char[9];
    private int currentPlayer;
    int emptyCells;

    TicTacToe() {
        refresh();
    }

    protected TicTacToe(Parcel in) {
        in.readCharArray(cells);
        currentPlayer = in.readInt();
        emptyCells = in.readInt();
    }

    public static final Creator<TicTacToe> CREATOR = new Creator<TicTacToe>() {
        @Override
        public TicTacToe createFromParcel(Parcel in) {
            return new TicTacToe(in);
        }

        @Override
        public TicTacToe[] newArray(int size) {
            return new TicTacToe[size];
        }
    };

    public void refresh() {
        currentPlayer = 0;
        emptyCells = 9;
        for (int i = 0; i < 9; ++i) cells[i] = '.';
    }

    private void changeTurn() {
        currentPlayer = 1 - currentPlayer;
    }

    private boolean isWon() {
        for (int i = 0; i < 3; ++i) {
            if (cells[i] != '.' && cells[i] == cells[i + 3] && cells[i + 3] == cells[i + 6])
                return true;
        }
        for (int i = 0; i < 9; i += 3) {
            if (cells[i] != '.' && cells[i] == cells[i + 1] && cells[i + 1] == cells[i + 2])
                return true;
        }
        if (cells[0] != '.' && cells[0] == cells[4] && cells[4] == cells[8])
            return true;
        return cells[2] != '.' && cells[2] == cells[4] && cells[4] == cells[6];
    }

    /**
     * @return 0 on incorrect move, 1 on correct move, 2 on player's win and 3 on draw
     */
    public int makeMove(int cellNumber) {
        if (cells[cellNumber] != '.') return 0;
        cells[cellNumber] = getSymbol(currentPlayer);
        emptyCells--;
        if (isWon()) return 2;
        if (emptyCells == 0) return 3;
        changeTurn();
        return 1;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public char getSymbol(int player) {
        if (player == 0) return 'X';
        return 'O';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeCharArray(cells);
        dest.writeInt(currentPlayer);
        dest.writeInt(emptyCells);
    }

    public char getCellSymbol(int cellNum){
        return cells[cellNum];
    }
}
