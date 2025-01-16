package com.affenbande.affenbandeBackend.helper

import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import java.time.ZonedDateTime


// TODO: make variable
val timeZoneEu = ZoneId.of("Europe/Berlin")
val formatterDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

val zonedDateTime = ZonedDateTime.now(ZoneId.of("Europe/Berlin"))
val formattedZonedDateTime = zonedDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)


fun getDateFromLocalDateTime(dateTime: LocalDateTime): String? {
    // 1. Date only (yyyy-MM-dd):
    val dateFormatter = DateTimeFormatter.ISO_DATE
    val dateString = dateTime.format(dateFormatter)
    return dateString
}

fun getTimeFromLocalDateTime(dateTime: LocalDateTime): String? {
    // 2. Time only (HH:mm:ss):
    val timeFormatter = DateTimeFormatter.ISO_TIME
    val timeString = dateTime.format(timeFormatter)
    return timeString
}

fun getAmericanDateTimeFromLocalDateTime(dateTime: LocalDateTime): String {

    // 3. Date and Time (yyyy-MM-ddTHH:mm:ss - ISO_LOCAL_DATE_TIME is the standard)
    val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
    val dateTimeString = dateTime.format(dateTimeFormatter)
    return dateTimeString
}

fun getCustomDateTimeFromLocalDateTime(dateTime: LocalDateTime): String {

    //4. Custom format:
    val customFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
    val customFormattedDateTime = dateTime.format(customFormatter)

    return customFormattedDateTime
}
