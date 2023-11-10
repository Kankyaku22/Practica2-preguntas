package com.example.aplicacionverdaderofalso.ui.theme

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class StadisticsActivity : AppCompatActivity(){
    private val quizViewModel by viewModels<QuizViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            com.example.aplicacionverdaderofalso.ui.theme.ui.theme.AplicacionVerdaderoFalsoTheme {
                // La interfaz de la actividad de estadísticas
                StatisticsScreen()
            }
        }
    }

    @Composable
    fun StatisticsScreen() {
        val correctAnswers = quizViewModel.getCorrectAnswers()
        val incorrectAnswers = quizViewModel.getIncorrectAnswers()
        val totalClicks = quizViewModel.getTotalClicks()
        val accuracyPercentage = quizViewModel.getAccuracyPercentage()
        val errorPercentage = quizViewModel.getErrorPercentage()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Estadísticas del Usuario", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Aciertos Totales: $correctAnswers")
            Text("Fallos Totales: $incorrectAnswers")
            Text("Número de Clicks Totales: $totalClicks")
            Text("Porcentaje de Aciertos: %.2f%%".format(accuracyPercentage))
            Text("Porcentaje de Fallos: %.2f%%".format(errorPercentage))
        }
    }
}