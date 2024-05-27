package ru.tinkoff.favouritepersons.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.views.DumpViewsInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.tinkoff.favouritepersons.PreferenceRule
import ru.tinkoff.favouritepersons.database.CacheCleaner
import ru.tinkoff.favouritepersons.database.DatabaseHelper
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.KaspressoAddStudentScreen
import ru.tinkoff.favouritepersons.screens.KaspressoMainScreen

// TEST CASE 13
// тест отлавливает 2 ошибки, если ловим NullPointerException при проверке на hasDrawable, значит, что фотографии нету,
// если словили AssertionFailedError, значит фотка появилась

//почему то даже после очиски кеша фотка появляется, если запустить тест-кейс, предварительно выключив интернет на эмуляторе все пройдет
// если интренет будет включен, фотография без интернета все равно появится и тест упадет
@RunWith(AndroidJUnit4::class)
class UploadPhotoNoInternetTest : TestCase(
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
    val activityScenarioRule = activityScenarioRule<MainActivity>()


    @Before
    fun editDatabase() {
        device.network.toggleWiFi(false)
        device.network.toggleMobileData(false)
        activityScenarioRule.scenario.onActivity { activity ->
            DatabaseHelper.clearDatabase(activity)
        }
        CacheCleaner.deleteDir(device.targetContext.cacheDir)
    }

    @Test
    fun uploadPhotoNoInternet() = run {
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
            enterPhotoLink("https://avatars.cloudflare.steamstatic.com/889fb67f2d7f50e8d8997f3f854a25ab8722d094_full.jpg")
            enterScore("100")
            clickSave()
        }
        Assert.assertTrue(mainScreen.checkPhotoNotVisible())
        device.network.toggleWiFi(true)
        device.network.toggleMobileData(true)
        Assert.assertFalse(mainScreen.checkPhotoNotVisible())
    }

}