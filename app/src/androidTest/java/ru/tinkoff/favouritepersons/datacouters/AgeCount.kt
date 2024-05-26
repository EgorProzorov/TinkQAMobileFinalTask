package ru.tinkoff.favouritepersons.datacouters

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

object AgeCount {

    fun calculateAge(birthdate: String): Int {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val birthDate = LocalDate.parse(birthdate, formatter)
        val currentDate = LocalDate.now()
        return Period.between(birthDate, currentDate).years
    }
    fun createFutureDate() : String{

        val currentDate = LocalDate.now()
        return currentDate.plusYears(1).toString()
    }
}