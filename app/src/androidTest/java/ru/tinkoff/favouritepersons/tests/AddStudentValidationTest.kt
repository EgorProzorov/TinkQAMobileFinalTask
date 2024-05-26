package ru.tinkoff.favouritepersons.tests

import androidx.test.ext.junit.rules.activityScenarioRule

import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.views.DumpViewsInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import ru.tinkoff.favouritepersons.PreferenceRule
import ru.tinkoff.favouritepersons.database.DatabaseHelper
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

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun editDatabase() {
        activityScenarioRule.scenario.onActivity { activity ->
            DatabaseHelper.clearDatabase(activity)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters() // параметры для тестирования и сообщение об ошибке которое ожидаем
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
    fun addValidation() = run {
        val mainScreen = KaspressoMainScreen()
        mainScreen.clickAddMenu()
        mainScreen.clickAddManually()
        val addScreen = KaspressoAddStudentScreen()
        addScreen.apply {
            fillAllInsteadOne(fieldName)

            clickSave()

            checkErrorMessage(errorMessage)
        }

    }

}