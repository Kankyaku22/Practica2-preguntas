package com.example.aplicacionverdaderofalso.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplicacionverdaderofalso.R

class main : ComponentActivity() {
    enum class Answer {
        TRUE, FALSE
    }
    enum class ButtonState {
        NORMAL,
        CORRECT,
        INCORRECT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            com.example.aplicacionverdaderofalso.ui.theme.ui.theme.AplicacionVerdaderoFalsoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   QuestionScreen()
                }
            }
        }
    }


    data class Question(val text: String, val imageRes: Int, val answer: Answer)
    val questions = listOf(
        Question("¿Bob Esponja trabaja en el crustáceo crujiente?", R.drawable.bob, Answer.TRUE),
        Question("¿Calamardo es guapo?", R.drawable.calamar, Answer.TRUE),
        Question("¿Patricio va elegante?", R.drawable.estrella, Answer.TRUE),
        Question("¿El señor Cangrejo es avaricioso?", R.drawable.cangrejo, Answer.TRUE),
        Question("¿La señora Puff es la profesora de baloncesto de Bob?", R.drawable.puff, Answer.FALSE)
    )
    @Composable
    fun getButtonColor(buttonState: ButtonState): Color {
        return when (buttonState) {
            ButtonState.NORMAL -> Color.Transparent
            ButtonState.CORRECT -> Color.Green
            ButtonState.INCORRECT -> Color.Red
        }
    }
    @Composable
    fun QuestionScreen() {
        var currentQuestionIndex by remember { mutableStateOf(0) }
        var showMessage by remember { mutableStateOf(false) }
        var resultMessage by remember { mutableStateOf("") }
        var selectedAnswer by remember { mutableStateOf(null as Answer?) }
        var trueButtonState by remember { mutableStateOf(ButtonState.NORMAL) }
        var falseButtonState by remember { mutableStateOf(ButtonState.NORMAL) }

        fun checkAnswer() {

            if (selectedAnswer == questions[currentQuestionIndex].answer) {
                resultMessage = "Correcto"
                trueButtonState = ButtonState.CORRECT
                falseButtonState = ButtonState.INCORRECT

            } else {
                resultMessage = "Incorrecto, esa no era la respuesta correcta, intentalo de nuevo"
                trueButtonState = ButtonState.CORRECT
                falseButtonState = ButtonState.INCORRECT
            }
            showMessage = true
        }
        fun showRandomQuestion() {
            val randomIndex = (0 until questions.size).random()
            currentQuestionIndex = randomIndex
            showMessage = false
            selectedAnswer = null
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.Black),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val currentQuestion = questions[currentQuestionIndex]

            Text(
                text = currentQuestion.text,
                style = TextStyle(fontSize = 25.sp,
                    textAlign = TextAlign.Center),
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()


            )

            Image(
                painter = painterResource(id = currentQuestion.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(10.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = {
                        selectedAnswer = Answer.TRUE
                        checkAnswer()
                    },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .background(getButtonColor(trueButtonState))


                ) {
                    Text(text = "VERDADERO")
                }
                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {
                        selectedAnswer = Answer.FALSE
                        checkAnswer()
                    },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .background(getButtonColor(falseButtonState))
                ) {
                    Text(text = "FALSO")
                }
            }

            if (showMessage) {
                Text(
                    text = resultMessage,
                    color = if (resultMessage == "Correcto") Color.Green else Color.Red,
                    style = TextStyle(fontSize = 18.sp)
                )
            }
           Row (
               horizontalArrangement = Arrangement.spacedBy(16.dp)
)          {
               Button(
                onClick = {
                    currentQuestionIndex = (currentQuestionIndex - 1) % questions.size
                    showMessage = false
                    selectedAnswer = null
                    trueButtonState = ButtonState.NORMAL
                    falseButtonState = ButtonState.NORMAL
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier

                    .weight(1f)


            ) {

                Icon(Icons.Default.ArrowBack, contentDescription = "Anterior")
                   Text(text = "PREV")
            }
               Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    currentQuestionIndex = (currentQuestionIndex + 1) % questions.size
                    showMessage = false
                    selectedAnswer = null
                    trueButtonState = ButtonState.NORMAL
                    falseButtonState = ButtonState.NORMAL
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(1f)


            ) {
                Text(text = "NEXT")
                Icon(Icons.Default.ArrowForward, contentDescription = "Siguiente")

            }
        }
            Button(
                onClick = {
                    showRandomQuestion()
                    trueButtonState = ButtonState.NORMAL
                    falseButtonState = ButtonState.NORMAL
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "RANDOM")
            }

        }

    }


    @Composable
    fun MyApp() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            QuestionScreen()
        }
    }

    @Preview
    @Composable
    fun MyAppPreview() {
        MyApp()
    }
}

