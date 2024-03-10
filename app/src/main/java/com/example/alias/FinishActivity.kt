package com.example.alias

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class FinishActivity : AppCompatActivity() {
    private lateinit var finalText: TextView
    private lateinit var againButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        finalText = findViewById(R.id.final_text)
        againButton = findViewById(R.id.again_button)

        if (counterTeamOne > counterTeamTwo) {
            finalText.text =
                "Победила команда $teamOneText счёт $counterTeamOne \n" +
                        " команда $counterTeamTwo счёт $counterTeamTwo"
        }
        if (counterTeamTwo > counterTeamOne) {
            finalText.text = "Победила команда $teamTwoText счёт $counterTeamTwo  \n" +
                    " команда $counterTeamOne счёт $counterTeamOne"
        }

        againButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            counterTeamOne = 0
            counterTeamTwo = 0
            rounds = 0
        }
    }
}