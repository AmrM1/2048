package com.example.a2048;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game(this);
        game.startGame();
    }

    public void clickRestartButton(View view)
    {
        game.restartGame();
    }

    public void clickStepBackButton(View view)
    {
        game.stepBackGame();
    }

}
