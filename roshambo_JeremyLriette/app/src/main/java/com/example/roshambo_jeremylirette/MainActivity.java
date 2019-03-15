package com.example.roshambo_jeremylirette;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roshambo_jeremylriette.R;

import static com.example.roshambo_jeremylirette.Rochambo.PAPER;
import static com.example.roshambo_jeremylirette.Rochambo.ROCK;
import static com.example.roshambo_jeremylirette.Rochambo.SCISSOR;

public class MainActivity extends AppCompatActivity {

    Rochambo gameManager;
    ImageView player;
    ImageView game;
    TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameManager = new Rochambo();
        player = findViewById(R.id.playerMove);
        game = findViewById(R.id.gameMove);
        results = findViewById(R.id.gameResults);
    }

    //When player chooses rock
    public void rockClick(View view) {
        player.setImageResource(R.drawable.rock);
        runGame(0);
    }

    //When player chooses paper
    public void paperClick(View view) {
        player.setImageResource(R.drawable.paper);
        runGame(1);
    }

    //When player chooses scissors
    public void scissorsClick(View view) {
        player.setImageResource(R.drawable.scissors);
        runGame(2);
    }

    //Animate the choices
    public void animateMoves(View player, View game){

        ObjectAnimator animatorPlayer = ObjectAnimator.ofFloat(player,
                "rotationX", 0f, 360f)
                .setDuration(500);

        ObjectAnimator animatorGame = ObjectAnimator.ofFloat(game,
                "rotationY", 0f, 360f)
                .setDuration(500);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorGame, animatorPlayer);
        set.setInterpolator(new AnticipateOvershootInterpolator());
        set.start();
    }

    //Accepts the player's choice, runs game logic
    public void runGame(int playerChoice){
        //Set player choice
        gameManager.playerMakesMove(playerChoice);
        //Receive result
        int result = gameManager.winLoseOrDraw();
        //Receive game move
        int gameMove = gameManager.getGameMove();
        //Set image based on game move
        switch(gameMove){
            case ROCK:
                game.setImageResource(R.drawable.rock);
                break;
            case PAPER:
                game.setImageResource(R.drawable.paper);
                break;
            case SCISSOR:
                game.setImageResource(R.drawable.scissors);
                break;
        }
        //Animate the moves
        animateMoves(player,game);
        //Set results text
        results.setText(result);
    }
}
