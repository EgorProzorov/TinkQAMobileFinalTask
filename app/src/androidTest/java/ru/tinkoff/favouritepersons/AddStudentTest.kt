package ru.tinkoff.favouritepersons

import androidx.test.ext.junit.rules.activityScenarioRule

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.views.DumpViewsInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.KaspressoMainScreen



class KaspressoAddStudentTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple(
        customize = {
            flakySafetyParams = FlakySafetyParams.custom(timeoutMs = 6_000, intervalMs = 250)
        }
    ).apply {
        testRunWatcherInterceptors.addAll(listOf(DumpViewsInterceptor(viewHierarchyDumper)))
    }
) {
    @get:Rule
    val prefs = PreferenceRule()

//    @get: Rule
//    val mock = WireMockRule(5000)

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun add() = run {
        val mainScreen = KaspressoMainScreen()
        mainScreen.clickAddManually()
        mainScreen.clickAddManually()
        Thread.sleep(5000)

//        stubFor( // мокаем для того чтобы работать только с одним именем и получать всегда один ответ
//            post(
//                urlPathEqualTo("/api/core/cats/add"))
//                .willReturn(aResponse()
//                    .withStatus(200)
//                    .withBody(fileToString("add_cat.json"))))
//
//
   }


}