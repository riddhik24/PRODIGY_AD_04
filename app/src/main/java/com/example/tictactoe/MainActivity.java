package com.example.tictactoe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    MaterialButton resetbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        LayoutInflater inflater= LayoutInflater.from(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        resetbtn=findViewById(R.id.reset);
        resetbtn.setOnClickListener(this::gameReset);
    }
    int [][] winPositions= {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8},{2,4,6}};
    boolean gameActive=true;
    int acivePlayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    public void playerTap(View view) {
        if(!gameActive ){
            gameReset(view);
        }
        ImageView img = (ImageView) view;
        int tappedImage= Integer.parseInt(img.getTag().toString());
        if(gameState[tappedImage]==2){

            gameState[tappedImage]=acivePlayer;
            img.setTranslationY(-1000f);

            if(acivePlayer==0){
                img.setImageResource(R.drawable.xx);
                acivePlayer=1;
                TextView status= findViewById(R.id.status);
                status.setText("0's Turn - Tap to play");
            }else {
                img.setImageResource(R.drawable.o);
                acivePlayer = 0;
                TextView status= findViewById(R.id.status);
                status.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        for(int[] winPositions: winPositions){
            if(gameState[winPositions[0]] == gameState[winPositions[1]] &&
                    gameState[winPositions[1]] == gameState[winPositions[2]] &&
                        gameState[winPositions[0]] != 2){
                            String winnerStr;
                            gameActive=false;

                            if(gameState[winPositions[0]]==0){
                                winnerStr ="X has won";
                                Toast.makeText(this, "X has Won! Click anywhere in boxes to restart.", Toast.LENGTH_SHORT).show();
                            }else{
                                winnerStr ="0 has won";
                                Toast.makeText(this, "0 has Won! Click anywhere in boxes to restart.", Toast.LENGTH_SHORT).show();
                            }
                            TextView status= findViewById(R.id.status);
                            status.setText(winnerStr);
            }
        }

        boolean emptySquare = false;
        for (int squareState : gameState) {
            if (squareState == 2) {
                emptySquare = true;
                break;
            }
        }
        if (!emptySquare && gameActive) {
            // Game is a draw
            gameActive = false;
            String winnerStr;
            winnerStr = "No one won";
            Toast.makeText(this, "It's a draw! Click anywhere in boxes to restart.", Toast.LENGTH_SHORT).show();
            TextView status = findViewById(R.id.status);
            status.setText(winnerStr);
        }
    }

    private void gameReset(View view) {
        gameActive = true;
        acivePlayer = 0;
        for (int i = 0; i < gameState.length; i++){
            gameState[i]=2;
        }
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        Toast.makeText(this, "Game Restarted!", Toast.LENGTH_SHORT).show();
        TextView status= findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");
    }
}