package ru.tinkoff.favouritepersons.screens

import android.util.Log
import android.view.View
import com.kaspersky.kaspresso.device.phone.Phone
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import ru.tinkoff.favouritepersons.R


class KaspressoMainScreen : KScreen<KaspressoMainScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    private val  addPersonManuallyButton = KButton{withId(R.id.fab_add_person_manually)}

    private val personList = KRecyclerView(
        builder = { withId(R.id.rv_person_list) },
        itemTypeBuilder = { itemType(::PersonRating) }
    )

    fun clickAddManually(){
    addPersonManuallyButton.click()
    }


    fun checkIfOpen(){
        personList.isDisplayed()
    }
    fun checkUser(name : String, privateInfo : String, email : String, phone: String, address : String, score : String){
        personList.childAt<PersonRating>(0){
            this.personName.hasText(name)
            this.personPrivateInfo.hasText(privateInfo)
            this.personEmail.hasText(email)
            this.personPhone.hasText(phone)
            this.personAddress.hasText(address)
            this.personRating.hasText(score)
        }
    }
}
private class PersonRating(matcher: org.hamcrest.Matcher<View>) : KRecyclerItem<PersonRating>(matcher) {
    val personName = KTextView(matcher) { withId(R.id.person_name) }
    val personPrivateInfo = KTextView(matcher) { withId(R.id.person_private_info) }
    val personEmail = KTextView(matcher) { withId(R.id.person_email) }
    val personPhone = KTextView(matcher) { withId(R.id.person_phone) }
    val personAddress = KTextView(matcher) { withId(R.id.person_address) }
    val personRating = KTextView(matcher){withId(R.id.person_rating)}
}