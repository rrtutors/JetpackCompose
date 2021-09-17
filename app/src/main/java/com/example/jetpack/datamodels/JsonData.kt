package com.example.jetpack.datamodels

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

data class JsonData(val param1: String?, val param2: String?)

object JsonDataOps {
    fun toJson(data: JsonData?): String? {
        try {
            if (data != null) {
                val gsonBuilder = GsonBuilder()
                gsonBuilder.serializeNulls()
                val gson = gsonBuilder.create()
                return gson.toJson(data)
            }

        } catch (e: java.lang.Exception) {

        }
        return null
    }

    fun fromStringToData(value: String?): JsonData? {
        try {
            if (value != null) {
                val typeToken = object : TypeToken<JsonData>() {}.type
                val gsonBuilder = GsonBuilder()
                gsonBuilder.serializeNulls()
                val gson = gsonBuilder.create()
                if (gson != null) {
                    val data: JsonData? = gson.fromJson<JsonData>(value, typeToken)
                    return data
                }
            }
        } catch (e: java.lang.Exception) {

        }
        return null
    }
}
