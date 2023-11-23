package uz.itschool.mathgame.shared



import android.content.Context

class Sharedpref private constructor(context: Context) {
    private val shared = context.getSharedPreferences("app_shared", 0)
    private val edit = shared.edit()

    companion object{
        private var instance : Sharedpref? = null
        fun getInstance(context: Context): Sharedpref {
            if (instance == null) instance = Sharedpref(context)
            return instance!!
        }
    }

    fun getRecord(level: Int): Int{
        return shared.getInt("record$level", 0)
    }

    fun setRecord(level: Int, score:Int){
        edit.putInt("record$level", score).apply()
    }
}