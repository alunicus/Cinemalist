package com.github.alunicus.cinemalist

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File

class ResourceLoader(val basePath: String) {
    val gson: Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    inline fun <reified T> readFromJson(fileName: String): T {
        val file = this::class.java.classLoader?.getResource(basePath + fileName)!!.file

        val jsonText = File(file).readText(Charsets.UTF_8).trimIndent()

        return gson.fromJson<T>(jsonText, T::class.java)
    }
}