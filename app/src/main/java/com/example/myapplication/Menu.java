package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        ImageButton receipt = (ImageButton)findViewById(R.id.dann5);
        ImageButton favorites = (ImageButton)findViewById(R.id.dann6);
        ImageButton zaglushka = (ImageButton)findViewById(R.id.dann7);
        ImageButton changer = (ImageButton)findViewById(R.id.dann8);

        receipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                receipt.setImageResource(R.drawable.knopkaser);
                new CountDownTimer(300, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        receipt.setImageResource(R.drawable.knopkaser_nazh);
                        Intent i = new Intent(Menu.this, Receipts.class);
                        startActivity(i);
                    }
                }.start();
            }
        });

        favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favorites.setImageResource(R.drawable.knopkaser);
                new CountDownTimer(300, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        favorites.setImageResource(R.drawable.knopkaser_nazh);
                    }
                }.start();
            }
        });

        zaglushka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zaglushka.setImageResource(R.drawable.knopkaser);
                new CountDownTimer(300, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        zaglushka.setImageResource(R.drawable.knopkaser_nazh);
                    }
                }.start();
            }
        });

        changer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changer.setImageResource(R.drawable.knopkaser);
                new CountDownTimer(300, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        changer.setImageResource(R.drawable.knopkaser_nazh);
                    }
                }.start();
            }
        });

        //Бэтмен аккордеон на ютубе бомба
    }
}