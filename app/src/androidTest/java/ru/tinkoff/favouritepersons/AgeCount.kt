package ru.tinkoff.favouritepersons

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
}