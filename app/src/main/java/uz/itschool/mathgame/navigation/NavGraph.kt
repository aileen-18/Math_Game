package uz.itschool.mathgame.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uz.itschool.mathgame.screen.HomeScreen
import uz.itschool.mathgame.screen.IntroScreen
import uz.itschool.mathgame.screen.LevelScreen
import uz.itschool.mathgame.screen.RecordScreen
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

    }
}