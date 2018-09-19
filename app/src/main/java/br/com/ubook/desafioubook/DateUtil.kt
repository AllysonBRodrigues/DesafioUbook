package br.com.ubook.desafioubook

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun convertStringToDateTime(dateInString: String?): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", formatLocaleBrazil())
        val date = formatter.parse(dateInString) as Date
        val newFormat = SimpleDateFormat("dd/MM/yyyy", formatLocaleBrazil())
        return newFormat.format(date)
    }

    fun convertStringToYear(dateInString: String?): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", formatLocaleBrazil())
        val date = formatter.parse(dateInString) as Date
        val newFormat = SimpleDateFormat("yyyy", formatLocaleBrazil())
        return newFormat.format(date)
    }


    fun formatLocaleBrazil(): Locale {
        return Locale("pt","BR")
    }


}