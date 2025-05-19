package com.example.androidtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidtask.ui.theme.AndroidTaskTheme
import org.jetbrains.annotations.NotNull

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val questions = mutableListOf(
            Question("T/F: Is Android an operating system", true),
            Question("T/F: Earth is flat.", false),
            Question("T/F: Kotlin is officially supported for Android development", true)

        )


        setContent {
            var answer by rememberSaveable { mutableStateOf<Boolean?>(null) }
            var questionIdx by rememberSaveable { mutableStateOf(0) }


            AndroidTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        if (questionIdx < questions.size) {
                            QuestionPrint(text = questions[questionIdx].question)

                            if(answer != null){
                                var isCorrect = (answer == questions[questionIdx].answer)
                                AnswerCircle(isCorrect)
                                Button(onClick = {
                                    questionIdx++
                                    answer = null
                                         }) {
                                    Text("Next Question")
                                }
                            } else
                                Row {
                                    Button(onClick = { answer = true}) {
                                        Text("True")
                                    }

                                    Spacer(modifier = Modifier.width(40.dp))

                                    Button(onClick = {answer = false}) {
                                        Text("False")
                                    }
                                }
                        }
                        else{
                            Text("Quiz done")
                        }


                    }
                }
            }
        }
    }
}

@Composable
fun QuestionPrint(text: String,  modifier: Modifier = Modifier) {
    Text(
        text = "$text?",
        style = MaterialTheme.typography.headlineLarge
    )
}
data class Question(
    val question: String,
    val answer: Boolean
)
@Composable
fun AnswerCircle(answer: Boolean) {
    val color = if (answer == false) Color.Red else Color.Green
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(200.dp)
            .background(color)
    )
}


@Composable fun test(){

}
