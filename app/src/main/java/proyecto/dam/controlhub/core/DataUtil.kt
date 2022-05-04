package proyecto.dam.controlhub.core

import java.util.*
import java.util.Calendar.*

object DataUtil {

    private var rightNow: Calendar? = Calendar.getInstance()
    private var month = rightNow?.get(MONTH)
    private var day = rightNow?.get(DAY_OF_MONTH)
    private var year = rightNow?.get(YEAR)

    fun getMoth(): Int? {
        return (month?.plus(1))
    }

    fun getYear(): Int? {
        return year
    }

    fun getDay(): Int? {
        return day
    }

    fun getData(): String {
        return "$day/$month/$year"
    }


}

