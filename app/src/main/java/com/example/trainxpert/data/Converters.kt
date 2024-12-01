package com.example.trainxpert.data


import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Date

class Converters {
//    @TypeConverter
//    fun fromTimestamp(value: Long?): Date? {
//        return value?.let { Date(it) }
//    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    // Convertir un timestamp (Long) en LocalDateTime
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return value?.let {
            LocalDateTime.ofEpochSecond(it, 0, ZoneOffset.UTC)
        }
    }

    // Convertir un LocalDateTime en timestamp (Long)
    @TypeConverter
    fun localDateTimeToTimestamp(dateTime: LocalDateTime?): Long? {
        return dateTime?.toEpochSecond(ZoneOffset.UTC)
    }
}
