package com.mediading.stealthystriver.db;

import java.util.Date;

import androidx.room.TypeConverter;

public class Converters {
    @TypeConverter
    public static Date fromTimeStap(Long value){
        return value ==null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date){
        return date == null ? null: date.getTime();
    }

}
