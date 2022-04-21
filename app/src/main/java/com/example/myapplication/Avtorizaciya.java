package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Avtorizaciya extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtorizaciya);
        ImageButton back = (ImageButton)findViewById(R.id.dann2);
        ImageButton confirm = (ImageButton)findViewById(R.id.dann5);

        TextView osh = (TextView)findViewById(R.id.osh1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.setImageResource(R.drawable.knopkaser);
                new CountDownTimer(300, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        back.setImageResource(R.drawable.knopkaser_nazh);
                        Intent i = new Intent(Avtorizaciya.this, MainActivity.class);
                        startActivity(i);
                    }
                }.start();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText login, pass;
                login = (EditText)findViewById(R.id.login);
                pass = (EditText)findViewById(R.id.login2);


                confirm.setImageResource(R.drawable.knopkaser);
                new CountDownTimer(300, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {

                        confirm.setImageResource(R.drawable.knopkaser_nazh);
                        String log1, pas1;
                        log1 = login.getText().toString().trim();
                        pas1 = pass.getText().toString().trim();
                        if((log1.equals("admin"))&&(pas1.equals("admin"))) {
                            Intent i = new Intent(Avtorizaciya.this, Menu.class);
                            startActivity(i);
                        }
                        else{
                            osh.setText("Неверные данные");
                        }
                    }
                }.start();
            }
        });
    }
}