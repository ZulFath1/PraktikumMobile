package com.example.modul1xml

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ivDice1 = findViewById<ImageView>(R.id.ivDice1)
        val ivDice2 = findViewById<ImageView>(R.id.ivDice2)
        val btnRoll = findViewById<Button>(R.id.btnRoll)

        btnRoll.setOnClickListener {
            val dice1 = (1..6).random()
            val dice2 = (1..6).random()

            ivDice1.setImageResource(getDiceImage(dice1))
            ivDice2.setImageResource(getDiceImage(dice2))

            if (dice1 == dice2) {
                Toast.makeText(this, "Yey dapat dobel", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Kada Hoki", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getDiceImage(diceValue: Int): Int {
        return when (diceValue) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_0
        }
    }
}