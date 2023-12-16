package com.github.kseodev.core

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class I18n {
    private val translationMap = mutableMapOf<String, Map<String, String>>()
    private val supportedLanguages = mutableListOf<String>()

    init {
        println(getResourceFiles("translation"))
    }

    private fun getResourceFiles(path: String): List<String> {
        // no i18n ;c
        return listOf()
    }
}