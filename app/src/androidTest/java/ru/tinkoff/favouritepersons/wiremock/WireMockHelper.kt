package ru.tinkoff.favouritepersons.wiremock

import android.content.Context
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

object WireMockHelper {

    fun fileToString(path: String, context: Context = InstrumentationRegistry.getInstrumentation().context): String {
        Log.d("fileToString", BufferedReader(InputStreamReader(context.assets.open(path), StandardCharsets.UTF_8)).readText())
        return BufferedReader(InputStreamReader(context.assets.open(path), StandardCharsets.UTF_8)).readText()
    }
}