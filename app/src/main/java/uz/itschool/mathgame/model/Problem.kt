package uz.itschool.mathgame.model

data class Problem(
    val question: String,
    val correctAnswer: Int,
    val wrongAnswers: List<Int>
)
