package com.example.sipirecipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sipirecipes.R
import android.widget.ImageButton
import android.os.CountDownTimer
import android.content.Intent
import android.view.View

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)
        val receipt = findViewById<View>(R.id.dann5) as ImageButton
        val favorites = findViewById<View>(R.id.dann6) as ImageButton
        val zaglushka = findViewById<View>(R.id.dann7) as ImageButton
        val changer = findViewById<View>(R.id.dann8) as ImageButton
        receipt.setOnClickListener {
            receipt.setImageResource(R.drawable.knopkaser)
            object : CountDownTimer(300, 1000) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    receipt.setImageResource(R.drawable.knopkaser_nazh)
                    val i = Intent(this@Menu, Receipts::class.java)
                    startActivity(i)
                }
            }.start()
        }
        favorites.setOnClickListener {
            favorites.setImageResource(R.drawable.knopkaser)
            object : CountDownTimer(300, 1000) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    favorites.setImageResource(R.drawable.knopkaser_nazh)
                }
            }.start()
        }
        zaglushka.setOnClickListener {
            zaglushka.setImageResource(R.drawable.knopkaser)
            object : CountDownTimer(300, 1000) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    zaglushka.setImageResource(R.drawable.knopkaser_nazh)
                }
            }.start()
        }
        changer.setOnClickListener {
            changer.setImageResource(R.drawable.knopkaser)
            object : CountDownTimer(300, 1000) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    changer.setImageResource(R.drawable.knopkaser_nazh)
                }
            }.start()
        }

        //Бэтмен аккордеон на ютубе бомба
    }
}