package com.example.Laboratorio4.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.projecto_suarez.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConvertor {
    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id},${source.name}"
    }
    @TypeConverter
    fun stringToSource(source: String): Source{
        return source.split(",").let { sourceArray ->
            Source(id = sourceArray[0], name = sourceArray[1])
        }
    }

}