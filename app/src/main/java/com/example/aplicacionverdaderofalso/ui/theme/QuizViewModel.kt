package com.example.aplicacionverdaderofalso.ui.theme

import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    private var correctAnswers = 0
    private var incorrectAnswers = 0
    private var totalClicks = 0

    fun recordCorrectAnswer() {
        correctAnswers++
        totalClicks++
    }

    fun recordIncorrectAnswer() {
        incorrectAnswers++
        totalClicks++
    }

    fun getTotalClicks(): Int {
        return totalClicks
    }

    fun getCorrectAnswers(): Int {
        return correctAnswers
    }

    fun getIncorrectAnswers(): Int {
        return incorrectAnswers
    }

    fun getAccuracyPercentage(): Float {
        return if (totalClicks > 0) {
            (correctAnswers.toFloat() / totalClicks.toFloat()) * 100
        } else {
            0f
        }
    }

    fun getErrorPercentage(): Float {
        return if (totalClicks > 0) {
            (incorrectAnswers.toFloat() / totalClicks.toFloat()) * 100
        } else {
            0f
        }
    }
}