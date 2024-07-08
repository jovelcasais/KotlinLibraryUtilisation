package com.jovelcasais.kotlinlibraryutilisation.utilities.helpers

import android.os.Build
import androidx.room.TypeConverter
import java.time.LocalDate

open class DateConverterHelper {

    @TypeConverter
    open fun toDate(dateString: String?): LocalDate? {
        return if (dateString == null) {
            null
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDate.parse(dateString)
            } else {
                TODO("VERSION.SDK_INT < O")
            }
        }
    }

    @TypeConverter
    open fun toDateString(date: LocalDate?): String? {
        return date?.toString()
    }

}