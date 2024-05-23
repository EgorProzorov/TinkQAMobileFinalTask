package ru.tinkoff.favouritepersons

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

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
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.KaspressoAddStudentScreen
import ru.tinkoff.favouritepersons.screens.KaspressoMainScreen

// TEST CASE 2
@RunWith(Parameterized::class) // параметризированный тест, заполянем все, кроме одного и ждем сообщения об ошибке
class AddStudentValidationTest(
    private val fieldName: String,
    private val errorMessage: String
) : TestCase(
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
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: Validate {0}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf("Name", "Поле должно быть заполнено!"),
                arrayOf("Surname", "Поле должно быть заполнено!"),
                arrayOf("Gender", "Поле должно быть заполнено буквами М или Ж"),
                arrayOf("Birthdate", "Поле должно быть заполнено в формате 1990-12-31"),
                arrayOf("Email", "Поле должно быть заполнено в формате mail@gmail.com"),
                arrayOf("Phone", "Поле должно быть заполнено!"),
                arrayOf("Address", "Поле должно быть заполнено!"),
                arrayOf("PhotoLink", "Поле должно быть заполнено!"),
                arrayOf("Score", "Поле должно быть заполнено двузначным числом")
            )
        }
    }

    @Test
    fun add() = run {
        val mainScreen = KaspressoMainScreen()
        mainScreen.clickAddManually()
        mainScreen.clickAddManually()
        val addScreen = KaspressoAddStudentScreen()
        addScreen.apply {
            if (fieldName != "Name") enterName("Billy")
            if (fieldName != "Surname") enterSurname("Herrington")
            if (fieldName != "Gender") enterGender("Male")
            if (fieldName != "Birthdate") enterBirthdate("1990-01-01")
            if (fieldName != "Email") enterEmail("billy@example.com")
            if (fieldName != "Phone") enterPhone("1234567890")
            if (fieldName != "Address") enterAddress("123 Street, City")
            if (fieldName != "PhotoLink") enterPhotoLink("https://image.tmdb.org/t/p/w235_and_h235_face/g4yz1mu1CV5irtLvXA5zXA1imBC.jpg")
            if (fieldName != "Score") enterScore("100")

            clickSave()

            checkErrorMessage(errorMessage)
        }

    }

}