package mx.mariovaldez.elashstudioapp.ktx

import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun getMonthDays(): List<String> {
    val formatter = SimpleDateFormat("dd", Locale.getDefault())
    val list = arrayListOf<String>()
    Calendar.getInstance().let { calendar ->
        calendar.add(Calendar.DAY_OF_MONTH, -30)
        for (i in 0 until 31) {
            list.add(formatter.format(calendar.timeInMillis))
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
    }
    return list.sorted()
}

fun getMonths(): List<String> {
    val symbols = DateFormatSymbols()
    return symbols.months.map {
        it.uppercase()
    }
}

fun getYearsRange(): List<String> {
    val interimDates = arrayListOf<String>()
    val initial = Date()
    var finalDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).parse("01-01-1930")
    val c = Calendar.getInstance()
    finalDate?.let {
        c.time = it
    }
    while (initial > finalDate) {
        with(c) {
            interimDates.add(get(Calendar.YEAR).toString())
        }
        c.add(Calendar.YEAR, 1)
        finalDate = c.time
    }
    return interimDates.sortedDescending()
}
