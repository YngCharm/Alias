package com.example.alias

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity


var teamOneText = ""
var teamTwoText = ""

class MainActivity : ComponentActivity() {
    private lateinit var startButton: Button
    private lateinit var teamOne: EditText
    private lateinit var teamTwo: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        startButton = findViewById(R.id.start_button)
        teamOne = findViewById(R.id.team_one)
        teamTwo = findViewById(R.id.team_two)

        startButton.setOnClickListener {
            teamOneText = teamOne.text.toString()
            teamTwoText = teamTwo.text.toString()
            if (teamOneText != "" && teamTwoText != "" && teamOneText != teamTwoText) {
                val intent = Intent(this, GameActivity::class.java)
                startActivity(intent)
            } else {
                showToast(this, "Назовите команды.")

            }
        }
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}