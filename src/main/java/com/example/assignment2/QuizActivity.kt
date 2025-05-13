package com.example.assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class QuizActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var feedBack: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button

    private val questions = arrayOf(
        "Nelson Mandela became South Africa's first Black president in 1994.",
        "Cape Town is the capital city of South Africa.",
        "The currency of South Africa is the Rand.",
        "South Africa has only one official language.",
        "The national animal of South Africa is the Springbok."
    )

    private val answers = arrayOf(true, false, true, false, true)

    private var index = 0
    private var score = 0
    private var answered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionTextView = findViewById(R.id.questionTextView)
        feedBack = findViewById(R.id.feedBack)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        nextButton = findViewById(R.id.nextButton)

        loadQuestion()

        trueButton.setOnClickListener {
            if (!answered) checkAnswer(true)
        }
        falseButton.setOnClickListener {
            if (!answered) checkAnswer(false)
        }

        nextButton.setOnClickListener {
            index++
            if (index < questions.size) {
                loadQuestion()
                feedBack.text = ""
                enableButtons()
                answered = false
            } else {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun loadQuestion() {
        questionTextView.text = questions[index]
    }

    private fun checkAnswer(userAnswer: Boolean) {
        answered = true
        disableButtons()
        if (userAnswer == answers[index]) {
            feedBack.text = "Correct!"
            score++
        } else {
            feedBack.text = "Incorrect!"
        }
    }

    private fun disableButtons() {
        trueButton.isEnabled = false
        falseButton.isEnabled = false
    }

    private fun enableButtons() {
        trueButton.isEnabled = true
        falseButton.isEnabled = true
    }
}

