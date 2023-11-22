package uz.itschool.mathgame.screen

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.itschool.mathgame.R
import uz.itschool.mathgame.model.Question
import uz.itschool.mathgame.navigation.NavGraph

@Composable
fun HomeScreen(navController: NavController) {
    var  level: Int=1
    val correct = remember { mutableIntStateOf(0) }
    val total = remember { mutableIntStateOf(0) }
    val question = remember { mutableStateOf(Question.generate(level)) }
    val coroutineScope = rememberCoroutineScope()


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
                        // savol raqamini ko'rsatadi
                        Text(
                            text = "1",
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.Center)
                        )

                        // scoreni ko'rsdatadi

                    }


                }
                Text(
                        text = "Score: ${correct.intValue}",
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = 22.sp,

                // Aligns the text to the end
                )

                //animation uchun

            }
//            Box(
//                contentAlignment = Alignment.BottomEnd,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp)
//            ) {
//                // Add your Lottie animation here
//                // LottieAnimation(
//                //     ...
//                // )
//            }
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(20.dp, 8.dp)
                            .width(220.dp)
                            .height(40.dp)
                    ) {
                        Text(
                            text = question.value.var1.toString(),
                            color = Color.White,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(23.dp))
                        Text(
                            text = question.value.var2.toString(),
                            color = Color.White,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = " = ",
                            color = Color.White,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = question.value.result.toString(),
                            color = Color.White,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )

                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .background(
                        Color(0xFF161C44),
                        shape = RoundedCornerShape(40.dp)
                    ),

                ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(modifier = Modifier.padding(0.dp, 6.dp, 0.dp, 0.dp)) {
                        SymbolButton(
                            0,
                            R.drawable.minus_icon,
                            correct = correct,
                            total = total,
                            question,
                            level,
                            coroutineScope,

                        )
                        SymbolButton(
                            1,
                            R.drawable.plus_icon,
                            correct = correct,
                            total = total,
                            question,
                            level,
                            coroutineScope,

                        )
                    }
                    Row {
                        SymbolButton(
                            2,
                            R.drawable.divide_icon,
                            correct = correct,
                            total = total,
                            question,
                            level,
                            coroutineScope,


                        )
                        SymbolButton(
                            3,
                            R.drawable.multiply_icon,
                            correct = correct,
                            total = total,
                            question,
                            level,
                            coroutineScope,

                        )
                    }
                }
            }

                }


            }


        }

@Composable
fun RowScope.SymbolButton(
    id: Int,
    imageResource: Int,
    correct: MutableIntState,
    total: MutableIntState,
    question: MutableState<Question>,
    levelId: Int,
    coroutineScope: CoroutineScope,


) {
    Button(
        modifier = Modifier
            .weight(1f, true)
            .aspectRatio(1f, true)
            .padding(8.dp),
        onClick = {
            total.intValue++
            coroutineScope.launch {
                if (question.value.symbol == id) {
                    correct.intValue++
                    delay(400)

                } else {
                    delay(1600)

                }
                question.value = Question.generate(levelId)
            }



        },
        shape = RoundedCornerShape(64.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)

    ) {
        Image(painter = painterResource(id = imageResource), contentDescription = "minus icon")
    }
}



@Preview(showBackground = true)
@Composable
fun showHome(){
    val navController = rememberNavController()
    NavGraph(navController = navController)
    HomeScreen(navController = navController)
}