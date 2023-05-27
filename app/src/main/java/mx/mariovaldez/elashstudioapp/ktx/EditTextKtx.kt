package mx.mariovaldez.elashstudioapp.ktx

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.EditText
import mx.mariovaldez.elashstudioapp.R
import java.math.BigDecimal
import java.text.NumberFormat

internal fun EditText.addCardFormatter() {
    val nonDigits = "[^\\d]".toRegex()
    var current = ""
    addTextChangedListener(
        object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun afterTextChanged(editable: Editable?) {
                if (editable?.toString() != current) {
                    val userInput = editable.toString().replace(nonDigits, "")
                    if (userInput.length <= 16) {
                        current = userInput.chunked(4).joinToString(" ")
                        editable?.filters = arrayOfNulls<InputFilter>(0)
                    }
                    editable?.replace(0, editable.length, current, 0, current.length)
                }
            }
        }
    )
}

internal fun EditText.addCurrencyFormatter() {
    var current = ""
    addTextChangedListener(
        object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun afterTextChanged(editable: Editable?) {
                if (editable?.toString() != current) {
                    val cleanString = editable?.replace("[$,.]".toRegex(), "")
                    cleanString?.toDoubleOrNull()?.let { doubleParsed ->
                        removeTextChangedListener(this)
                        val parsedNumber = BigDecimal(doubleParsed)
                            .setScale(2, BigDecimal.ROUND_FLOOR)
                            .divide(BigDecimal(100), BigDecimal.ROUND_FLOOR)
                        val amountFormatted =
                            NumberFormat.getCurrencyInstance().format(parsedNumber)
                        current = amountFormatted
                        setText(amountFormatted)
                        setSelection(amountFormatted.length)
                        addTextChangedListener(this)
                    }
                }
            }
        }
    )
}

internal fun EditText.showEditTextError() {
  //  setBackgroundResource(R.drawable.error_edit_text_background)
}

internal fun EditText.hideEditTextError() {
   // setBackgroundResource(R.drawable.normal_edit_text_background)
}
