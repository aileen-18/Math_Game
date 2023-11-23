package uz.itschool.mathgame.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import uz.itschool.mathgame.model.Result
import uz.itschool.mathgame.screen.HomeScreen
import uz.itschool.mathgame.screen.IntroScreen
import uz.itschool.mathgame.screen.LevelScreen
import uz.itschool.mathgame.screen.RecordScreen
import uz.itschool.mathgame.screen.ResultScreen
import uz.itschool.mathgame.screen.SplashScreen


@Composable
fun NavGraph (navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route)
    {
        composable(route = Screens.Splash.route){
            SplashScreen(navController)
        }
        composable(route = Screens.Intro.route){
           IntroScreen(navController)
        }

        composable(route = Screens.Home.route){
            HomeScreen(navController)
        }
        composable(route = Screens.Level.route){
           LevelScreen(navController)
        }
        composable(route = Screens.Record.route){
            RecordScreen(navController)
        }
        composable(route = Screens.Result.route,
            arguments = listOf(
                navArgument("correct"){
                    type = NavType.IntType
                },navArgument("incorrect"){
                    type = NavType.IntType
                },navArgument("level"){
                    type = NavType.IntType
                },navArgument("new_record"){
                    type = NavType.BoolType
                },
            )
        ) { entry ->
            val correct = entry.arguments?.getInt("correct")!!
            val incorrect = entry.arguments?.getInt("incorrect")!!
            val level = entry.arguments?.getInt("level")!!
            val newRecord = entry.arguments?.getBoolean("new_record")!!
           ResultScreen(navController = navController, result =Result(correct =correct, incorrect = incorrect, level=level, newRecord =newRecord))
        }

    }
}