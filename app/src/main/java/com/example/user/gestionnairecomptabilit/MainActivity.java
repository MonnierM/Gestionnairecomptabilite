package com.example.user.gestionnairecomptabilit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
    {
    private ImageView picAssos, picCompta;
    private TextView bienvenue, createur ;
    Animation frombottom, fromtop, fromleft, fromright;
    private static int SPLASH_TIME_OUT = 3200;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        picAssos = findViewById( R.id.imageView2 );
        picCompta = findViewById( R.id.imageView );
        bienvenue = findViewById( R.id.tv_bienvenue );
        createur = findViewById( R.id.tv_createur );
        frombottom = AnimationUtils.loadAnimation( this,R.anim.frombottom );
        fromtop = AnimationUtils.loadAnimation( this, R.anim.fromtop );
        fromleft = AnimationUtils.loadAnimation( this,R.anim.fromright );
        fromright = AnimationUtils.loadAnimation( this, R.anim.fromleft );

        picAssos.setAnimation(fromleft);
        picCompta.setAnimation(fromright);
        bienvenue.setAnimation(fromtop);
        createur.setAnimation(frombottom);

        new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
        Intent loginIntent = new Intent(MainActivity.this, Login.class);
        startActivity(loginIntent);
        finish();
        }
        },SPLASH_TIME_OUT);

        }
    }
