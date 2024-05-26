package ru.tinkoff.favouritepersons.tests

import android.util.Log
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.anyUrl
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.junit.WireMockRule

import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.views.DumpViewsInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.tinkoff.favouritepersons.PreferenceRule
import ru.tinkoff.favouritepersons.R
import ru.tinkoff.favouritepersons.database.DatabaseHelper
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.KaspressoAddStudentScreen
import ru.tinkoff.favouritepersons.screens.KaspressoMainScreen

// TEST CASE 7
@RunWith(AndroidJUnit4::class) // надо мокать
class EditPhotoTest : TestCase(
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
    @get:Rule
    val mock = WireMockRule(5000)

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun editDatabase() {
        activityScenarioRule.scenario.onActivity { activity ->
            DatabaseHelper.clearDatabase(activity)
            DatabaseHelper.addUser(activity)
        }
    }
    @Test
    fun editPhoto() = run {

        val mainScreen = KaspressoMainScreen()
        mainScreen.clickFirstStudent()
        val addStudent = KaspressoAddStudentScreen()
        addStudent.enterPhotoLink("https://masterpiecer-images.s3.yandex.net/70401d977baa11eeb26cbadf81d486ab:upscaled")
        addStudent.clickSave()
        mainScreen.checkAvatar()
    }
}