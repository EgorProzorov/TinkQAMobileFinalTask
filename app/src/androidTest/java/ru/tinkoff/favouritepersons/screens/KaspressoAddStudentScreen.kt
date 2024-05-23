package ru.tinkoff.favouritepersons.screens

import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.edit.KTextInputLayout
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import ru.tinkoff.favouritepersons.R


class KaspressoAddStudentScreen : KScreen<KaspressoMainScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    //Elements
    private val  nameField = KTextInputLayout{withId(R.id.til_name)}
    private val  surnameField = KTextInputLayout{withId(R.id.til_surname)}
    private val  genderField = KTextInputLayout{withId(R.id.til_gender)}
    private val  birthdateField = KTextInputLayout{withId(R.id.til_birthdate)}
    private val  emailField = KTextInputLayout{withId(R.id.til_email)}
    private val  phoneField = KTextInputLayout{withId(R.id.til_phone)}
    private val  addressField = KTextInputLayout{withId(R.id.til_address)}
    private val  photoLinkField = KTextInputLayout{withId(R.id.til_image_link)}
    private val  scoreField = KTextInputLayout{withId(R.id.til_score)}
    private val saveButton = KButton{withText("Сохранить")}
    //Actions
    fun enterName(name : String){
        nameField.edit.replaceText(name)
    }
    fun enterSurname(surname: String) {
        surnameField.edit.replaceText(surname)
    }

    fun enterGender(gender: String) {
        genderField.edit.replaceText(gender)
    }

    fun enterBirthdate(birthdate: String) {
        birthdateField.edit.replaceText(birthdate)
    }

    fun enterEmail(email: String) {
        emailField.edit.replaceText(email)
    }

    fun enterPhone(phone: String) {
        phoneField.edit.replaceText(phone)
    }

    fun enterAddress(address: String) {
        addressField.edit.replaceText(address)
    }

    fun enterPhotoLink(photoLink: String) {
        photoLinkField.edit.replaceText(photoLink)
    }

    fun enterScore(score: String) {
        scoreField.edit.replaceText(score)
    }

    fun clickSave() {
        saveButton.click()
    }

    //Asserts
    fun checkErrorMessage(message : String){
        KTextView {
            withText(message)
        }.isVisible()
    }

}