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
import uz.itschool.mathgame.navigation.NavGraph
import java.util.Random

@Composable
fun HomeScreen(navController: NavController) {
    val score = remember { mutableIntStateOf(0) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF222853)),
        ) {
            // App bar
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = com.airbnb.lottie.R.color.material_blue_grey_800)),
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

                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, true)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .background(
                        color = colorResource(id = com.airbnb.lottie.R.color.material_blue_grey_800),
                        shape = RoundedCornerShape(64.dp)
                    ),

                ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {

                    Row(modifier = Modifier.padding(0.dp, 6.dp, 0.dp, 0.dp)) {
                        SymbolButton(0, R.drawable.minus_icon, score)
                        SymbolButton(1, R.drawable.plus_icon, score)
                    }
                    Row {
                        SymbolButton(2, R.drawable.divide_icon, score)
                        SymbolButton(3, R.drawable.multiply_icon, score)
                    }
                }
            }


        }


    }
}
@Composable
fun RowScope.SymbolButton(id: Int, imageResource: Int, scoreVar: MutableIntState) {
    Button(
        modifier = Modifier
            .weight(1f, true)
            .aspectRatio(1f, true)
            .padding(8.dp),
        onClick = {
            onSymbolClick(id, scoreVar)
        },
        shape = RoundedCornerShape(64.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)

    ) {
        Image(painter = painterResource(id = imageResource), contentDescription = "minus icon")
    }
}
fun onSymbolClick(id: Int, scoreVar: MutableIntState) {
    scoreVar.intValue += 1
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