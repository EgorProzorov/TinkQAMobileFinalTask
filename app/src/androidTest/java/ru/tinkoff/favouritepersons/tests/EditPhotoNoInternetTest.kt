package ru.tinkoff.favouritepersons.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.tomakehurst.wiremock.junit.WireMockRule

import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.views.DumpViewsInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.tinkoff.favouritepersons.AgeCount
import ru.tinkoff.favouritepersons.PreferenceRule
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.KaspressoAddStudentScreen
import ru.tinkoff.favouritepersons.screens.KaspressoMainScreen

// TEST CASE 9
@RunWith(AndroidJUnit4::class)
class EditPhotoNoInternetTest : TestCase( // падает, не ловим тоаст с предупреждением об отсутствии интернета
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

    @get: Rule
    val mock = WireMockRule(5000)

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun editPhotoNoInternet() = before {
        device.network.toggleWiFi(false)
        device.network.toggleMobileData(false)
        device.context.cacheDir.delete()

    }.after {
        device.network.toggleWiFi(true)
        device.network.toggleMobileData(true)
    }.run {
        val mainScreen = KaspressoMainScreen()

        mainScreen.clickAddMenu()
        mainScreen.clickAddManually()

        val addScreen = KaspressoAddStudentScreen()
        addScreen.apply {
            enterName("Billy")
            enterSurname("Herrington")
            enterGender("Мужской")
            enterBirthdate("1990-01-01")
            enterEmail("billy@example.com")
            enterPhone("1234567890")
            enterAddress("Our hearts")
            enterPhotoLink("https://steamuserimages-a.akamaihd.net/ugc/2301969478603184329/ED862B8FB36DCA804AB8EFC5B062FC71126E8E4C/?imw=637&imh=358&ima=fit&impolicy=Letterbox&imcolor=%23000000&letterbox=true")
            enterScore("100")
            clickSave()
        }
        mainScreen.checkInternetToast()
    }




//        stubFor( // мокаем для того чтобы работать только с одним именем и получать всегда один ответ
//            post(
//                urlPathEqualTo("/api/core/cats/add"))
//                .willReturn(aResponse()
//                    .withStatus(200)
//                    .withBody(fileToString("add_cat.json"))))
//
//
}