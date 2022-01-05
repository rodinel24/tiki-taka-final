package me.jacoblewis.tic_tac_toe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import me.jacoblewis.tic_tac_toe.databinding.ActivityMainBinding
import me.jacoblewis.tic_tac_toe.models.Board
import me.jacoblewis.tic_tac_toe.models.BoardState
import me.jacoblewis.tic_tac_toe.models.Cell

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn_start)
        btn.setOnClickListener{
            val intent = Intent(this@MainActivity, Homepage::class.java)
            startActivity(intent)
            finish()
        }
    }
}