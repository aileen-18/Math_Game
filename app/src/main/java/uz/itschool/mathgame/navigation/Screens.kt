package uz.itschool.mathgame.navigation

sealed class Screens(val route: String){
    object Splash: Screens("splash_screen")
    object Intro: Screens("intro_screen")
    object Level: Screens("level_screen")
    object Home: Screens("home_screen")
    object Record : Screens("record_screen")
}

