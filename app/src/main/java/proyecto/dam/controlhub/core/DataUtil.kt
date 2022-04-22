package proyecto.dam.controlhub.core

import java.util.*
import java.util.Calendar.*

class DataUtil {

        private var rightNow: Calendar? = Calendar.getInstance()
        private var month = rightNow?.get(MONTH)
        private var day = rightNow?.get(DAY_OF_MONTH)
        private var year = rightNow?.get(YEAR)

        public fun getMoth() : Int? {
            return (month?.plus(1))
        }
        public fun getYear() : Int? {
            return year
        }
        public fun getDay() : Int? {
            return day
        }


    }

