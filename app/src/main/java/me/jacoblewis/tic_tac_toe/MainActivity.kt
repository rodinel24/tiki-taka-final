package me.jacoblewis.tic_tac_toe

import android.content.Intent
import android.os.Bundle
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn_start)
        btn.setOnClickListener{
            val intent = Intent(this@MainActivity, SinglePlayer::class.java)
            startActivity(intent)
            finish()
        }
    }
}