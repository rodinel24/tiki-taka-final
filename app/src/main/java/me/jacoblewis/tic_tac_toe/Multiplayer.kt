package me.jacoblewis.tic_tac_toe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog


class Multiplayer: AppCompatActivity(), View.OnClickListener {
    //original   private val button: Array<Array<Button?>> = Array<Array<Button?>>(3) { arrayOfNulls<Button>(3) }
    private val button: Array<Array<Button?>> = Array(3) { arrayOfNulls(3) }
    private var player1turn = true
    private var roundcount = 0
    private var player1Points = 0
    private var player2Points = 0
    private lateinit var tvplayer1: TextView
    private lateinit var tvplayer2: TextView
    private var playerFirst =""
    private var playerSecond =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.multiplayer_activity)



        tvplayer1 = findViewById(R.id.tv_player1)
        tvplayer2 = findViewById(R.id.tv_player2)

        getPlayerName()
        for (i in 0..2) {
            for (j in 0..2) {
                val buttonId = "button_$i$j"
                val resId = resources.getIdentifier(buttonId, "id", packageName)

                button[i][j] = findViewById(resId)
                button[i][j]!!.setOnClickListener(this)


            }
        }
        val resetbtn = findViewById<Button>(R.id.button_reset)
        resetbtn.setOnClickListener { resetGame() }
    }

    private fun resetGame() {
        player1Points = 0
        player2Points = 0
        updatepointstext()
        resetBoard()

    }

    private fun getPlayerName() {
        val infalter = LayoutInflater.from(this@Multiplayer)
        val plView = infalter.inflate(R.layout.players_info, null)
        val player1name = plView.findViewById<EditText>(R.id.first_player)
        val player2name = plView.findViewById<EditText>(R.id.second_player)
        val pldialog = AlertDialog.Builder(this@Multiplayer)
        pldialog.setView(plView)
        pldialog.setPositiveButton("Add"){dialog,_->
            if(player1name.text.toString().isEmpty()
                && player2name.text.toString().isEmpty())
            {
                playerFirst = "Player 1"
                playerSecond = "Player 2"

                tvplayer1.text = "$playerFirst : 0"
                tvplayer2.text = "$playerSecond : 0"

            }
            else
            {
                playerFirst = player1name.text.toString()
                playerSecond = player2name.text.toString()

                tvplayer1.text = "$playerFirst : 0"
                tvplayer2.text = "$playerSecond : 0"
            }
        }
        pldialog.setNeutralButton("Cancel"){
                dialog,_->
            playerFirst = "Player 1"
            playerSecond = "Player 2"

            tvplayer1.text = "$playerFirst : 0"
            tvplayer2.text = "$playerSecond : 0"
        }
        pldialog.create()
        pldialog.show()
    }

    override fun onClick(v: View?) {


        if ((v as Button).text.toString() != "") {
            return
        }
        if (player1turn) {
            (v as Button).text = "x"

        } else {
            (v as Button).text = "o"

        }
        roundcount++
        if (checkforwin()) {
            if (player1turn) {
                player1win()
            } else {
                player2win()
            }
        } else if (roundcount == 9) {
            draw()
        } else {
            player1turn = !player1turn
        }

    }

    private fun draw() {
        AlertDialog.Builder(this).setMessage("DRAW!").setPositiveButton("Ok"){dialog,_-> resetBoard()}
            .setNeutralButton("Cancel"){dialog,_-> resetBoard()}
            .create().show()
    }

    private fun player1win() {

        player1Points++
        updatepointstext()
        resetBoard()
    }
    private fun player2win() {
        player2Points++
        updatepointstext()
        resetBoard()
    }
    private fun resetBoard() {
        for (i in 0..2)
        {
            for (j in 0..2)
            {
                button[i][j]!!.text = ""
            }
        }
        roundcount = 0
        player1turn = false
    }

    private fun updatepointstext() {
        tvplayer1.text = "$playerFirst: $player1Points"
        tvplayer2.text = "$playerSecond: $player2Points"
    }



    private fun checkforwin(): Boolean {

        val field = Array(3){ arrayOfNulls<String>(3)}

        for(i in 0..2)
        {
            for(j in 0..2)
            {
                field[i][j] = button[i][j]!!.text.toString()
            }
        }


        for (i in 0..2)
        {
            if(field[i][0]== field[i][1] && field[i][0] == field[i][2] && field[i][0]!="")
            {
                return true
            }
        }
        for (i in 0..2)
        {
            if(field[0][i]== field[1][i] && field[0][i] == field[2][i] && field[0][i]!="")
            {
                return true
            }
        }
        if(field[0][0]== field[1][1] && field[0][0] == field[2][2] && field[0][0]!="")
        {
            return true
        }

        return field[0][2]== field[1][1] && field[0][2] == field[2][0] && field[0][2]!=""
    }


    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt("Round count" , roundcount)
        outState.putInt("Player 1 Points" , player1Points)
        outState.putInt("Player 2 Points" , player2Points)
        outState.putBoolean("Player 1 Turn" , player1turn)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        roundcount = savedInstanceState.getInt("Round count")
        player1Points = savedInstanceState.getInt("Player 1 Points")
        player2Points = savedInstanceState.getInt("Player 2 Points")
        player1turn = savedInstanceState.getBoolean("Player 1 Turn")

    }
}














