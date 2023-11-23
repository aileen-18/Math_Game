package uz.itschool.mathgame.screen


import android.util.Log
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uz.itschool.mathgame.R
import uz.itschool.mathgame.model.Result
import uz.itschool.mathgame.shared.Sharedpref


@Preview
@Composable
fun PreviewResultScreen() {
    ResultScreen(rememberNavController(), uz.itschool.mathgame.model.Result(14, 2, 2, true))
}

fun getLevelName(id: Int): String {
    return when (id) {
        0 -> "Easy"
        1 -> "Medium"
        else -> "hard"
    }
}

@Composable
fun ResultScreen(navController: NavController, result: Result) {
    val appBarColor = colorResource(id =R.color.blue_appbar)
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher

    BackHandler(enabled = true, onBack = {
        navController.navigate("intro_screen")
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = appBarColor)
                .padding(0.dp, 8.dp)
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            BackButton(backDispatcher)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val shared = Sharedpref.getInstance(LocalContext.current)
            if (result.newRecord) {
                Log.d("TAG", "shared: ${shared.getRecord(result.level)}")
                Box(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.white),
                        )
                        .border(
                            border = BorderStroke(
                                4.dp, colorResource(
                                    id = R.color.purple_500
                                )
                            ), shape = RoundedCornerShape(64.dp)
                        )
                        .padding(24.dp, 8.dp)
                ) {
                    Text(
                        text = "New Record",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = colorResource(id = R.color.purple_500)
                    )
                }
            }
            Text(
                text = "Score: ${result.correct}",
                fontSize = 48.sp,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(0.dp, 16.dp)
            )
            Box(
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.white),
                    )
                    .border(
                        border = BorderStroke(
                            2.dp, colorResource(
                                id = com.airbnb.lottie.R.color.material_blue_grey_800
                            )
                        ), shape = RoundedCornerShape(20.dp)
                    )
                    .padding(24.dp, 8.dp)
            ) {
                Text(
                    text = "Level: ${getLevelName(result.level)}",
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.black),
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Total solved: ${result.correct + result.incorrect}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(
                    id = R.color.blue_appbar
                )
            )
            Text(
                text = "Incorrect: ${result.incorrect}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(
                    id = R.color.blue_appbar
                )
            )
            if (result.correct != 0 && result.incorrect != 0) Text(
                text = "Accuracy: ${result.correct * 100 / (result.correct + result.incorrect)}%",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(
                    id = R.color.blue_appbar
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row {
                Button(
                    onClick = { navController.navigate("intro_screen") },
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.teal_200)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(Icons.Rounded.Home, contentDescription = "Go home")
                    Text(text = "Home")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.purple_200)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(Icons.Rounded.Refresh, contentDescription = "Go home")
                    Text(text = "Restart ")
                }

            }
        }
    }
}


@Composable
fun BackButton(backPressedDispatcher: OnBackPressedDispatcher) {
    Button(
        onClick = {
            backPressedDispatcher.onBackPressed()
        },
        shape = CircleShape,
        modifier = Modifier
            .size(45.dp)
            .padding(),
        contentPadding = PaddingValues(4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
    ) {
        Icon(
            painterResource(id = R.drawable.back_icon),
            contentDescription = "Back button",
            tint = colorResource(id = R.color.blue_appbar)
        )
    }
}