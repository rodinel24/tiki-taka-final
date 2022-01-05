package me.jacoblewis.tic_tac_toe


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.view.View
import android.widget.Button

class PreferencePage: AppCompatActivity(),View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preference_page)

                val single = findViewById<Button>(R.id.btn_singleplayer)
                val multi = findViewById<Button>(R.id.btn_multiplayer)


        single.setOnClickListener {
            startActivity(Intent( this@PreferencePage, SinglePlayer::class.java))
        }


        multi.setOnClickListener {
            startActivity(Intent( this@PreferencePage, Multiplayer::class.java))
        }


    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}


