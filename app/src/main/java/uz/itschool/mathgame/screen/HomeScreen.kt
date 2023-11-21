package uz.itschool.mathgame.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import uz.itschool.mathgame.R
import uz.itschool.mathgame.model.Problem
import uz.itschool.mathgame.navigation.NavGraph
import java.util.Random

@Composable
fun HomeScreen(navController: NavController) {
    val score = remember { mutableIntStateOf(0) }

    val problems = remember { (0 until 10).map { generateRandomProblem() } }

    // State variable to keep track of the current problem index
    var currentProblemIndex by remember { mutableStateOf(0) }

    // Get the current problem
    val currentProblem = problems[currentProblemIndex]


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        ) {
            // App bar
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF161C44)),
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(45.dp)
                            .clip(shape = RoundedCornerShape(18.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        //savol raqamini ko'rsatadi
                        Text(
                            text = "${currentProblemIndex + 1}",
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                    }
                }

                //animation uchun
                Box(
                    contentAlignment = Alignment.BottomEnd,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    // Add your Lottie animation here
                    // LottieAnimation(
                    //     ...
                    // )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(300.dp)
                        .clip(shape = RoundedCornerShape(18.dp))
                        .background(Color.White)
                        .padding(top = 40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.board),
                        contentDescription = null,
                        modifier = Modifier
                            .size(300.dp)
                            .clip(shape = RoundedCornerShape(18.dp)),

                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = currentProblem.question,
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .background(
                        Color(0xFF161C44),
                        shape = RoundedCornerShape(50.dp)
                    ),

                ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(modifier = Modifier.padding(0.dp, 6.dp, 0.dp, 0.dp)) {
                        SymbolButton(0, R.drawable.minus_icon, score, currentProblem) {
                            onNextProblem()
                        }
                        SymbolButton(1, R.drawable.plus_icon, score, currentProblem) {
                            onNextProblem()
                        }
                    }
                    Row {
                        SymbolButton(2, R.drawable.divide_icon, score, currentProblem) {
                            onNextProblem()
                        }
                        SymbolButton(3, R.drawable.multiply_icon, score, currentProblem) {
                            onNextProblem()
                        }
                    }
                }


            }


        }
    }
    @Composable
    fun SymbolButton(
        id: Int,
        imageResource: Int,
        scoreVar: MutableIntState,
        currentProblem: Problem,
        onNextProblem: () -> Unit
    ) {
        Button(
            modifier = Modifier
                .weight(1f, true)
                .aspectRatio(1f, true)
                .padding(8.dp),
            onClick = {
                onSymbolClick(id, scoreVar, currentProblem, onNextProblem)
            },
            shape = RoundedCornerShape(64.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Image(painter = painterResource(id = imageResource), contentDescription = "minus icon")
        }
    }
}
    fun onSymbolClick(
        id: Int,
        scoreVar: MutableIntState,
        currentProblem: Problem,
        onNextProblem: () -> Unit
    ) {
        val selectedAnswer = when (id) {
            0 -> currentProblem.wrongAnswers[0]
            1 -> currentProblem.wrongAnswers[1]
            2 -> currentProblem.wrongAnswers[2]
            3 -> currentProblem.correctAnswer
            else -> throw IllegalArgumentException("Invalid id")
        }

        if (selectedAnswer == currentProblem.correctAnswer) {
            // Correct answer, update the score
            scoreVar.value += 1
        } else {
            // Incorrect answer, handle accordingly (e.g., show a message)
        }

        onNextProblem()
    }

    fun onNextProblem() {
        // Increment the current problem index or navigate to the ResultScreen if all problems are done
        currentProblemIndex++
        if (currentProblemIndex < problems.size) {
            // Get the next problem
            val nextProblem = problems[currentProblemIndex]
            // Do anything you need with the next problem
        } else {
            // All problems are done, navigate to the ResultScreen or handle as needed
            // For example, you can use navigation code here
            // navController.navigate("result_screen")
        }
    }

// savol tuzish
fun generateRandomProblem(): Problem {
    // Generate random numbers and operator
    val num1 = (0..10).random()
    val num2 = (0..10).random()
    val answer = ""
    val operator = listOf("+", "-", "*", "/").random()

    // Calculate correct answer
    val correctAnswer = when (operator) {
        "+" -> num1 + num2
        "-" -> num1 - num2
        "*" -> num1 * num2
        "/" -> num1 / num2
        else -> throw IllegalArgumentException("Invalid operator")
    }
// Generate wrong answers
    val wrongAnswers = mutableListOf<Int>()
    repeat(3) {
        wrongAnswers.add((correctAnswer - 5..correctAnswer + 5).random())
    }

    val useCorrectAnswer = (0..1).random() == 0
    val question = if (useCorrectAnswer) {
        "$num1 * $num2 = $correctAnswer"
    } else {
        val randomWrongAnswer = wrongAnswers.random()
        "$num1 * $num2 = $randomWrongAnswer"
    }

    return Problem(question, correctAnswer, wrongAnswers)
}



fun checkAnswer(problem: String, userAnswer: String): Boolean {
            try {
                // Split the problem into operands and operator
                val (operand1, operator, operand2) = problem.split(" ")

                // Convert operands to integers
                val num1 = operand1.toInt()
                val num2 = operand2.toInt()

                // Evaluate the result based on the operator
                val expectedResult = when (operator) {
                    "+" -> num1 + num2
                    "-" -> num1 - num2
                    "*" -> num1 * num2
                    "/" -> num1 / num2
                    else -> throw IllegalArgumentException("Invalid operator: $operator")
                }

                // Check if the user's answer is correct
                return userAnswer.toIntOrNull() == expectedResult
            } catch (e: Exception) {
                // Handle any parsing or arithmetic exceptions
                e.printStackTrace()
                return false
            }
        }





@Preview(showBackground = true)
@Composable
fun showHome(){
    val navController = rememberNavController()
    NavGraph(navController = navController)
    HomeScreen(navController = navController)
}