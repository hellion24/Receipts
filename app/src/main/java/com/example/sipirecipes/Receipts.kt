package com.example.sipirecipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sipirecipes.R
import android.widget.ImageButton
import android.os.CountDownTimer
import android.content.Intent
import android.view.View

class Receipts : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.receipts)
        val back = findViewById<View>(R.id.dann2) as ImageButton
        back.setOnClickListener {
            back.setImageResource(R.drawable.knopkaser)
            object : CountDownTimer(300, 1000) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    back.setImageResource(R.drawable.knopkaser_nazh)
                    val i = Intent(this@Receipts, Menu::class.java)
                    startActivity(i)
                }
            }.start()
        }

        //Бэтмен аккордеон на ютубе бомба
    }
}