package id.lilule.football.util

import id.lilule.football.util.Constants.DATE_FORMAT_ISO
import id.lilule.football.util.Constants.DATE_FORMAT_LONG
import id.lilule.football.util.Constants.DATE_ISO_GMT
import id.lilule.football.util.Constants.EMPTY_DATE
import id.lilule.football.util.Constants.EMPTY_TIME
import id.lilule.football.util.Constants.TIME_FORMAT_GMT
import id.lilule.football.util.Constants.TIME_FORMAT_LOCALE
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Date {
    fun dateIsoToLong(stringIsoDate: String?): String {
        return if (!stringIsoDate.isNullOrEmpty()) {
            val dateFormatISO = SimpleDateFormat(DATE_FORMAT_ISO, Locale.getDefault())
            dateFormatISO.isLenient = false
            return try {
                val dateParsed = dateFormatISO.parse(stringIsoDate)
                val dateFormatter = SimpleDateFormat(DATE_FORMAT_LONG, Locale.getDefault())
                dateFormatter.format(dateParsed)
            } catch (e: ParseException) {
                EMPTY_DATE
            }
        } else {
            EMPTY_DATE
        }
    }

    fun dateIsoGMTToLong(stringIsoDate: String?, stringGMT: String?): String {
        return if (!stringGMT.isNullOrEmpty()) {
            val timeString = timeWithTimezone(stringGMT)
            val dateTimeISOGMT = "$stringIsoDate $timeString"
            val dateISOGMT = SimpleDateFormat(DATE_ISO_GMT, Locale.getDefault())
            dateISOGMT.isLenient = false
            try {
                val dateParsed = dateISOGMT.parse(dateTimeISOGMT)
                val dateFormatter = SimpleDateFormat(DATE_FORMAT_LONG, Locale.getDefault())
                dateFormatter.format(dateParsed)
            } catch (e: ParseException) {
                EMPTY_DATE
            }
        } else {
            dateIsoToLong(stringIsoDate)
        }
    }

    fun timeGMTToLocale(stringGMT: String?): String {
        return if (!stringGMT.isNullOrEmpty()) {
            val timeFormatGMT = SimpleDateFormat(TIME_FORMAT_GMT, Locale.getDefault())
            timeFormatGMT.isLenient = false
            return try {
                val timeParsed = timeFormatGMT.parse(timeWithTimezone(stringGMT))
                val timeFormatter = SimpleDateFormat(TIME_FORMAT_LOCALE, Locale.getDefault())
                timeFormatter.format(timeParsed)
            } catch (e: ParseException) {
                EMPTY_TIME
            }
        } else {
            EMPTY_TIME
        }
    }

    fun timeInMillis(stringIsoDate: String?, stringGMT: String?): Long? {
        val timeString: String = stringGMT ?: "00:00:00+00:00"
        val timeWithTimeZone = timeWithTimezone(timeString)
        val dateTimeISOGMT = "$stringIsoDate $timeWithTimeZone"
        val dateISOGMT = SimpleDateFormat(DATE_ISO_GMT, Locale.getDefault())
        dateISOGMT.isLenient = false
        return try {
            val dateParsed = dateISOGMT.parse(dateTimeISOGMT)
            dateParsed.time
        } catch (e: ParseException) {
            null
        }
    }

    private fun timeWithTimezone(rawTime: String): String {
        val timeString = rawTime.split("+")[0]
        return "$timeString+00:00"
    }
}