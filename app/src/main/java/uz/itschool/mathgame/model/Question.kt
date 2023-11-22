package uz.itschool.mathgame.model

import kotlin.random.Random

class Question private constructor(
    var var1: Int,
    var var2: Int,
    var result: Int,
    var symbol: Int
) {

    companion object {
        fun generate(level: Int): Question {
            var limit = 11
            if (level == 1) limit = 21
            if (level == 2) limit = 51
            var num1 = Random.nextInt(1, limit)
            var num2 = Random.nextInt(1, limit)
            val symbol = Random.nextInt(4)

            if (num1 == 2 && num2 == 2) num1 = Random.nextInt(3, limit)

            if (symbol > 1){
                if (num1 == 1) num1 = Random.nextInt(3, limit)
                if (num2 == 1) num2 = Random.nextInt(2, limit)
            }

            if (level == 0 && symbol == 0){
                if (num1 < num2){
                    val k = num1
                    num1 = num2
                    num2 = k
                }
            }

            var res = when (symbol) {
                0 -> num1 - num2
                1 -> num1 + num2
                else -> num1 * num2
            }
            if (symbol == 2) {
                val k = num1
                num1 = res
                res = k
            }
            return Question(num1, num2, res, symbol)
        }
    }
}