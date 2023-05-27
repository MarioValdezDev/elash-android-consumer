package mx.mariovaldez.elashstudioapp.ktx

import android.util.Base64
import java.security.MessageDigest
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.random.Random

fun String.formatCompleteDate(): String = runCatching {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val date = dateFormat.parse(this)
    date?.let {
        with(calendar) {
            time = it
            val day = get(Calendar.DAY_OF_MONTH)
            val month = getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
            val year = get(Calendar.YEAR)
            val hour = get(Calendar.HOUR_OF_DAY)
            val minutes = get(Calendar.MINUTE)
            val seconds = get(Calendar.SECOND)
            "$day $month $year, $hour:$minutes:$seconds h"
        }
    } ?: ""
}.getOrElse { "" }

fun String.formatDate(year: Int, month: Int, dayOfWeek: Int): String = runCatching {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, dayOfWeek)
    val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
    dateFormat.format(calendar.time)
}.getOrElse { "" }

fun String.formatSimplifiedDate(): String = runCatching {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = dateFormat.parse(this)
    date?.let {
        with(calendar) {
            time = it
            val day = get(Calendar.DAY_OF_MONTH)
            val month = getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
            val year = get(Calendar.YEAR)
            "$day ${month?.capitalize(Locale.ROOT)}, $year"
        }
    } ?: ""
}.getOrElse { "" }

fun String.formatDueDate(): String = runCatching {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("MM/dd/yyyy HH:mm:ss a", Locale.US)
    val date = dateFormat.parse(this)
    date?.let {
        with(calendar) {
            time = it
            val month = get(Calendar.MONTH) + 1
            val year = get(Calendar.YEAR).toString().substring(2)
            "$month/$year"
        }
    } ?: ""
}.getOrElse { "" }

fun String.cardDigits(): String =
    if (this.isNotEmpty() && this.length >= 16) this.chunked(4).joinToString(" ") else ""

fun String.lastCardDigits(): String =
    if (this.isNotEmpty() && this.length > 4) this.substring(this.length - 4) else ""

fun String.formatToCurrency(): String {
    return this.toDoubleOrNull()?.let {
        val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        numberFormat.format(it)
    } ?: ""
}

fun String.toSha256(): String = MessageDigest.getInstance("SHA-256")
    .digest(this.toByteArray())
    .encodeToString()

fun String.decodeToByteArray(): ByteArray = Base64.decode(this, Base64.NO_WRAP)

fun String.formatAmount(): Double = this.drop(1).replace(",", "").toDouble()

fun String.formatMoneyCurrency(): String {
    val amount = this.toDouble()
    return NumberFormat.getCurrencyInstance(Locale("es", "MX")).format(amount)
}

fun String.formatLastCardsDigits(): String {
    return "•••• $this"
}

fun dateTransferFormat(): String {
    val date = Calendar.getInstance().time
    val dateFormat = SimpleDateFormat("yyyy/MM/dd, hh:mm a", Locale.US)
    return dateFormat.format(date)
}

fun hourTransferFormat(): String {
    val hour = Calendar.getInstance().time
    val dateFormat = SimpleDateFormat("hh:mm a", Locale.US)
    return dateFormat.format(hour)
}

fun String.phoneFormat(): String {
    return "+52${this.replace(" ", "")}"
}

fun String.firstNameFormat(): String {
    val names = this.split(" ")
    return names[0].replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

fun String.removeEmptySpaces(): String = filter { !it.isWhitespace() }

fun String.removeCurrencyFormat(): Double = try {
    NumberFormat.getCurrencyInstance().parse(this)?.toDouble() ?: 0.0
} catch (_: Throwable) {
    0.0
}

fun getReferenceNumber(): String = runCatching {
    val calendar = Calendar.getInstance().time
    val dateFormat = SimpleDateFormat("ddMMyy", Locale.US)
    val date = dateFormat.format(calendar)
    val num = Random.nextInt(0, 9)
    "$date$num"
}.getOrElse { "" }

fun matchDetails(inputString: String, whatToFind: String, startIndex: Int = 0): Int {
    return inputString.indexOf(whatToFind, startIndex)
}

fun getVideoName(): String = runCatching {
    val prefix = "personal-interview"
    val date = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.US)
        .format(System.currentTimeMillis())
    val wrapper = "mp4"
    "$prefix-$date.$wrapper"
}.getOrElse { "" }

fun String.validatePhoneNumber(): Boolean {
    var isValidate = true
    this.forEach { number ->
        this.forEach { c: Char ->
            isValidate = c != number
            if (isValidate)
                return true
        }
    }
    return isValidate
}
