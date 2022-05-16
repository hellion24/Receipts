package com.example.sipirecipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sipirecipes.R
import android.widget.ImageButton
import android.widget.TextView
import android.os.CountDownTimer
import android.content.Intent
import android.view.View
import android.widget.EditText

class Avtorizaciya : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avtorizaciya)
        val back = findViewById<View>(R.id.dann2) as ImageButton
        val confirm = findViewById<View>(R.id.dann5) as ImageButton
        val osh = findViewById<View>(R.id.osh1) as TextView
        back.setOnClickListener {
            back.setImageResource(R.drawable.knopkaser)
            object : CountDownTimer(300, 1000) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    back.setImageResource(R.drawable.knopkaser_nazh)
                    val i = Intent(this@Avtorizaciya, MainActivity::class.java)
                    startActivity(i)
                }
            }.start()
        }
        confirm.setOnClickListener {
            val login: EditText
            val pass: EditText
            login = findViewById<View>(R.id.login) as EditText
            pass = findViewById<View>(R.id.login2) as EditText
            confirm.setImageResource(R.drawable.knopkaser)
            object : CountDownTimer(300, 1000) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    confirm.setImageResource(R.drawable.knopkaser_nazh)
                    val log1: String
                    val pas1: String
                    log1 = login.text.toString().trim { it <= ' ' }
                    pas1 = pass.text.toString().trim { it <= ' ' }
                    if (log1 == "admin" && pas1 == "admin") {
                        val i = Intent(this@Avtorizaciya, Menu::class.java)
                        startActivity(i)
                    } else {
                        osh.text = "Неверные данные"
                    }
                }
            }.start()
        }
    }
}