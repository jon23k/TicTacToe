package edu.rose_hulman.kurianj.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TicTacToeGame mGame = new TicTacToeGame(this);
    private TextView mGameStateTextView;
    private Button[][] mTicTacToeButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGame.resetGame();

        mGameStateTextView = (TextView)findViewById(R.id.message_text);

        Button newGameButton = (Button)findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(this);

        mTicTacToeButtons = new Button[TicTacToeGame.NUM_ROWS][TicTacToeGame.NUM_COLUMNS];

        for(int i=0;i<TicTacToeGame.NUM_ROWS; i++)
        {
            for(int j=0; j<TicTacToeGame.NUM_COLUMNS; j++)
            {
                int id = getResources().getIdentifier("button"+i+j, "id", getPackageName());
                mTicTacToeButtons[i][j] = (Button)findViewById(id);
                mTicTacToeButtons[i][j].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.new_game_button)
        {
            mGame.resetGame();
        }
        for(int row=0;row<TicTacToeGame.NUM_ROWS;row++)
        {
            for(int col=0;col<TicTacToeGame.NUM_COLUMNS;col++)
            {

                if(v.getId() == mTicTacToeButtons[row][col].getId())
                {
                    Log.d("TTT", "Button Pressed at " + row + " " + col);
                    mGame.pressedButtonAtLocation(row, col);

                }
                mTicTacToeButtons[row][col].setText(mGame.stringForButtonAtLocation(row,col));
            }
        }
        mGameStateTextView.setText(mGame.stringForGameState());

    }
}
