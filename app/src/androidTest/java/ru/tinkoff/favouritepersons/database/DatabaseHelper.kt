package ru.tinkoff.favouritepersons.database

import android.content.Context
import kotlinx.coroutines.runBlocking
import ru.tinkoff.favouritepersons.domain.PersonItem
import ru.tinkoff.favouritepersons.room.PersonDataBase
import ru.tinkoff.favouritepersons.domain.Gender

object DatabaseHelper {

    fun clearDatabase(context: Context) {
        val db = PersonDataBase.getDBClient(context)
        runBlocking {
            db.personsDao().clearTable()
        }
    }
    fun addUser(context: Context){
        val db = PersonDataBase.getDBClient(context)
        runBlocking {
            db.personsDao().insertPerson(student)
        }
    }
    private val student = PersonItem(
        name = "Billy",
        surname = "Herrington",
        birthDate = "1990-01-01",
        gender = Gender.MALE,
        age = 34,
        email = "billy@example.com",
        phone = "1234567890",
        address = "Our hearts",
        imageLink = "https://steamuserimages-a.akamaihd.net/ugc/2301969478603184329/ED862B8FB36DCA804AB8EFC5B062FC71126E8E4C/?imw=637&imh=358&ima=fit&impolicy=Letterbox&imcolor=%23000000&letterbox=true",
        rating = 100
    )

}