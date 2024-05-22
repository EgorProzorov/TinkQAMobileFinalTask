package ru.tinkoff.favouritepersons.screens

import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KTextInputLayout
import io.github.kakaocup.kakao.text.KButton
import ru.tinkoff.favouritepersons.R


class KaspressoMainScreen : KScreen<KaspressoMainScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    private val  addPersonManuallyButton = KButton{withId(R.id.fab_add_person_manually)}

    fun clickAddManually(){
    addPersonManuallyButton.click()
    }

}