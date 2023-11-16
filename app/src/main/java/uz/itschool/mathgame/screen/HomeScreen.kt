package uz.itschool.mathgame.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import uz.itschool.mathgame.navigation.NavGraph
import java.util.Random

@Composable
fun HomeScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        var score by remember { mutableStateOf(0) }
        var questionIndex by remember { mutableStateOf(1) }

        var correctAnswer by remember { mutableStateOf("") }
        var answersForDisplay by remember { mutableStateOf<List<String>>(emptyList()) }
        var onAnswerSelected: (String) -> Unit = { userAnswer ->
            // Your logic here
            println("User selected: $userAnswer")
        }

        LaunchedEffect(true) {
            // Generate a random math problem with correct answer
            val (correctProblem, problemsForDisplay) = generateMathProblemWithAnswer()

            // Initialize onAnswerSelected lambda function
            onAnswerSelected = { userAnswer ->
                // Check the answer and update the score
                val isCorrect = userAnswer == correctProblem
                if (isCorrect) {
                    // Update the score
                    // You may want to use a ViewModel or other architecture for more complex apps
                    // This is a simplified example
                    score++
                }
                // Move to the next question or finish the game
                questionIndex++
                if (questionIndex > 10) {
                    // Navigate to ScoreScreen or perform any other action
                    // You may want to use navigation components for a more robust solution
                    // This is a simplified example
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Display the problems for the player to choose
            Text(
                text = problemsForDisplay,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            // Display buttons for operations (+, -, *, /)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Button for each problem (user's choices)
                MathOperationButton(correctProblem, onAnswerSelected)
            }
        }

    }
}
    @Composable
    fun MathOperationButton(
        symbol: String,
        onAnswerSelected: (String) -> Unit
    ) {
        Button(
            onClick = { onAnswerSelected(symbol) },
            modifier = Modifier
                .size(80.dp) // Adjust the button size as needed
                .padding(8.dp)


        ) {
            Text(symbol,
                fontSize = 20.sp)
        }
    }

fun generateMathProblemWithAnswer(): Pair<String, String>{
    val random = Random(System.currentTimeMillis())

    // Generate two random operands
    val operand1 = random.nextInt(10) // Adjust the range as needed
    val operand2 = random.nextInt(10) // Adjust the range as needed

    // Randomly select an operator
    val operators = listOf("+", "-", "*", "/")
    val operator = operators[random.nextInt(operators.size)]

    // Evaluate the result based on the operator
    val result = when (operator) {
        "+" -> operand1 + operand2
        "-" -> operand1 - operand2
        "*" -> operand1 * operand2
        "/" -> operand1 / operand2
        else -> throw IllegalArgumentException("Invalid operator: $operator")
    }

    // Create a problem with the correct answer
    val problemWithAnswer = "$operand1 $operator $operand2 = $result"

    // Shuffle the operators to create wrong answers
    val wrongOperators = operators.shuffled()

    // Create three wrong problems
    val wrongProblem1 = "$operand1 ${wrongOperators[0]} $operand2 = ${random.nextInt(20)}"
    val wrongProblem2 = "$operand1 ${wrongOperators[1]} $operand2 = ${random.nextInt(20)}"
    val wrongProblem3 = "$operand1 ${wrongOperators[2]} $operand2 = ${random.nextInt(20)}"

    // Randomly select the position for the correct answer
    val position = random.nextInt(4)

    // Insert the correct problem at the selected position
    val problems = mutableListOf(wrongProblem1, wrongProblem2, wrongProblem3)
    problems.add(position, problemWithAnswer)

    // Return the correct problem and the list of problems for UI display
    return Pair(problemWithAnswer, problems.joinToString(separator = "\n"))

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