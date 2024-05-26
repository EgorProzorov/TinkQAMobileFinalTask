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
            db.personsDao().insertPerson(student1)
        }
    }
    fun addUsers(context: Context){
        val db = PersonDataBase.getDBClient(context)
        runBlocking {
            db.personsDao().insertPerson(student1)
            db.personsDao().insertPerson(student2)
            db.personsDao().insertPerson(student3)
        }
    }
    private val student1 = PersonItem(
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
    private val student2 = PersonItem(
        name = "Ricardo",
        surname = "Milos",
        birthDate = "1977-11-11",
        gender = Gender.MALE,
        age = 46,
        email = "ricardo@example.com",
        phone = "1234567890",
        address = "internet memes",
        imageLink = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcShX3E7rZlHfS-3H8KzIG5efWq-_mZ-Z39UgQn7jEk5gw&s",
        rating = 77
    )
    private val student3 = PersonItem(
        name = "Van",
        surname = "Darkholme",
        birthDate = "1972-10-124",
        gender = Gender.MALE,
        age = 51,
        email = "van@example.com",
        phone = "1234567890",
        address = "dungeon",
        imageLink = "https://static.miraheze.org/notbyliewikiwiki/thumb/3/34/%D0%92%D0%B0%D0%BD_%D0%94%D0%B0%D1%80%D0%BA%D1%85%D0%BE%D0%BB%D1%8C%D0%BC.jpg/290px-%D0%92%D0%B0%D0%BD_%D0%94%D0%B0%D1%80%D0%BA%D1%85%D0%BE%D0%BB%D1%8C%D0%BC.jpg",
        rating = 50
    )

}