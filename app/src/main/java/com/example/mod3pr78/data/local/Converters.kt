package com.example.mod3pr78.data.local
import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromBoolean(value: Boolean): Int = if (value) 1 else 0
    @TypeConverter
    fun toBoolean(value: Int): Boolean = value == 1
}