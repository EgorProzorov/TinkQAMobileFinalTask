package ru.tinkoff.favouritepersons.screens

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import androidx.test.espresso.action.ViewActions
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import org.hamcrest.Matchers.containsString
import ru.tinkoff.favouritepersons.R


class KaspressoMainScreen : KScreen<KaspressoMainScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    private val addStudentMenu = KButton{withId(R.id.fab_add_person)}
    private val  addStudentManuallyButton = KButton{withId(R.id.fab_add_person_manually)}
    private val addStudentInternetButton = KButton{withId(R.id.fab_add_person_by_network)}
    private val sortButton = KButton{withId(R.id.action_item_sort)}
    private val sortByFullNameButton = KButton{withId(R.id.bsd_rb_name)}
    private val sortByRatingButton = KButton{withId(R.id.bsd_rb_rating)}
    private val toastNoInternet = KButton{withText(containsString("Internet error! Check your connection"))}

    private val personList = KRecyclerView(
        builder = { withId(R.id.rv_person_list) },
        itemTypeBuilder = { itemType(::PersonRating) }
    )

    fun clickAddMenu(){
        addStudentMenu.click()
    }
    fun clickAddManually(){
    addStudentManuallyButton.click()
    }
    fun clickAddInternet(){
        addStudentInternetButton.click()
    }

    fun checkIfOpen(){
        personList.isDisplayed()
    }

    fun checkUserData(name : String, privateInfo : String, email : String, phone: String, address : String, score : String){
        personList.childAt<PersonRating>(0){
            this.personName.hasText(name)
            this.personPrivateInfo.hasText(privateInfo)
            this.personEmail.hasText(email)
            this.personPhone.hasText(phone)
            this.personAddress.hasText(address)
            this.personRating.hasText(score)
        }
    }

    fun checkIfEmpty(){
        personList{hasSize(0)}
    }

    fun deleteStudent() {
        personList.childAt<PersonRating>(0){
            view.perform(ViewActions.swipeLeft())
        }
    }

    fun checkUserNotVisible(name: String){
        personList{childWith<PersonRating> {withText(containsString(name)) } perform {
            doesNotExist()
            }
        }
    }

    fun clickFirstStudent(){
        personList.childAt<PersonRating>(0){
            perform { click() }
        }
    }

    fun clickSortButton(){
        sortButton.click()
    }
    fun clickSortByFullName(){
        sortByFullNameButton.click()
    }

    fun clickSortByRating(){
        sortByRatingButton.click()
    }

    fun checkIfSortedByFullName() {
        personList.childAt<PersonRating>(0){
            this.personName.containsText("Milos")
        }
        personList.childAt<PersonRating>(1){
            this.personName.containsText("Herrington")
        }
        personList.childAt<PersonRating>(2){
            this.personName.containsText("Darkholme")
        }
    }
    fun checkIfSortedByRating() {
        personList.childAt<PersonRating>(0){
            this.personRating.containsText("100")
        }
        personList.childAt<PersonRating>(1){
            this.personRating.containsText("77")
        }
        personList.childAt<PersonRating>(2){
            this.personRating.containsText("50")
        }
    }
    fun checkAvatar(){
        personList.childAt<PersonRating>(0){
            this.personPhoto{
                isDisplayed()
                isVisible()
            }
        }
    }

    fun checkInternetToast(){
        toastNoInternet.isDisplayed()
    }

}
private class PersonRating(matcher: Matcher<View>) : KRecyclerItem<PersonRating>(matcher) {
    val personName = KTextView(matcher) { withId(R.id.person_name) }
    val personPrivateInfo = KTextView(matcher) { withId(R.id.person_private_info) }
    val personEmail = KTextView(matcher) { withId(R.id.person_email) }
    val personPhone = KTextView(matcher) { withId(R.id.person_phone) }
    val personAddress = KTextView(matcher) { withId(R.id.person_address) }
    val personRating = KTextView(matcher){withId(R.id.person_rating)}
    val personPhoto = KImageView(matcher){withId(R.id.person_avatar)}
}