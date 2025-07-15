package com.example.mestradobip;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;


import pl.droidsonroids.gif.GifDrawable;


public class MainActivity extends AppCompatActivity {
    CountDownTimer tempo;
    ImageView imV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        ImageView gifAnimado = findViewById(R.id.gifAnimado);
//
//        try {
//            // Carregar o GIF a partir de um arquivo na pasta drawable
//            GifDrawable gifFromResource = new GifDrawable(getResources(), R.drawable.nome_do_gif);
//            gifAnimado.setImageDrawable(gifFromResource);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intensao = new Intent(MainActivity.this, Tela_Login.class);
                startActivity(intensao);
                finish();
            }
        }, 4000);

    }






}