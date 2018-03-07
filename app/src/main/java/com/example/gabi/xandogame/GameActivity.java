package com.example.gabi.xandogame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    int activePlayer = 0; //0 = X player / 1= O player
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2}; //initialize free positions
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}}; //winning positions

    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gamestate[tappedCounter] == 2) {
            gamestate[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.x);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.o);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000).setDuration(300);
        }

        for (int[] winPosition : winPositions){
            if (gamestate[winPosition[0]] == gamestate[winPosition[1]] &&
                    gamestate[winPosition[1]] == gamestate[winPosition[2]] &&
                    gamestate[winPosition[0]] != 2){
               String winner = "Circle player won!";
               if (gamestate[winPosition[0]] == 0) {
                   winner = "X player won!";
               }
               printMessage(winner);
            }else{
                boolean gameIsOver = true;
                String draw = "Result is draw!";
                for(int verifier : gamestate){
                    if (verifier == 2) gameIsOver=false;
                }
                if (gameIsOver == true) printMessage(draw);
            }
        }

    }

    public void printMessage(String winner){
        TextView winnerText = (TextView) findViewById(R.id.winnerText);
        winnerText.setText(winner);

        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setTranslationY(-1000f);
        layout.setVisibility(View.VISIBLE);
        layout.animate().translationYBy(1000).setDuration(300);

    }

    public void playAgain(View view){
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i=0; i<gamestate.length; i++){
            gamestate[i] = 2;
        }

        GridLayout board = (GridLayout) findViewById(R.id.boardLayout);

        for(int i=0; i<board.getChildCount();i++){
            ((ImageView) board.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
}
