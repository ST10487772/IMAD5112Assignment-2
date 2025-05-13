package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    private lateinit var scoreTextView: TextView
    private lateinit var reviewText: TextView
    private lateinit var exitBtn: Button
    private lateinit var reviewButton: Button
    private lateinit var retryBtn: Button

    private val questions = arrayOf(
        "Nelson Mandela became South Africa's first Black president in 1994.",
        "Cape Town is the capital city of South Africa.",
        "The currency of South Africa is the Rand.",
        "South Africa has only one official language.",
        "The national animal of South Africa is the Springbok."
    )

    private val answers = arrayOf(true, false, true, false, true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        scoreTextView = findViewById(R.id.scoreTextView)
        reviewText = findViewById(R.id.reviewText)
        exitBtn = findViewById(R.id.exitBtn)
        reviewButton = findViewById(R.id.reviewButton)
        retryBtn = findViewById(R.id.retryBtn)

        val score = intent.getIntExtra("score", 0)
        val feedback = if (score >= 3) "Good job!" else "Keep trying!"
        scoreTextView.text = "$feedback Your score: $score / ${questions.size}"

        val review = StringBuilder()
        for (i in questions.indices) {
            review.append("${i + 1}. ${questions[i]}\nCorrect Answer: ${if (answers[i]) "True" else "False"}\n\n")
        }
        reviewText.text = review.toString()
        reviewText.visibility = View.GONE // Initially hidden

        // Show retry button only if score is less than 3
        if (score < 3) {
            retryBtn.visibility = View.VISIBLE
        } else {
            retryBtn.visibility = View.GONE
        }

        reviewButton.setOnClickListener {
            if (reviewText.visibility == View.GONE) {
                reviewText.visibility = View.VISIBLE
                reviewButton.text = "Hide Review"
            } else {
                reviewText.visibility = View.GONE
                reviewButton.text = "Show Review"
            }
        }

        retryBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java) // Replace with your quiz activity if it's not MainActivity
            startActivity(intent)
            finish()
        }

        exitBtn.setOnClickListener {
            finishAffinity()
        }
    }
}


