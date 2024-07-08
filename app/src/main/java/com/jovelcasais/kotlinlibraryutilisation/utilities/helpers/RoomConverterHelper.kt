package com.jovelcasais.kotlinlibraryutilisation.utilities.helpers

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class RoomConverterHelper {

    @TypeConverter
    open fun fromString(value: String) : List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    open fun fromList(list: List<String>): String {
        return Gson().toJson(list)
    }

}