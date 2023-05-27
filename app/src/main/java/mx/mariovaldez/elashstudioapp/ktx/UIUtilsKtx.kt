package mx.mariovaldez.elashstudioapp.ktx

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import mx.mariovaldez.elashstudioapp.ktx.hideEditTextError
import mx.mariovaldez.elashstudioapp.ktx.hideInputTextLayoutError
import mx.mariovaldez.elashstudioapp.ktx.setInputTextLayoutError
import mx.mariovaldez.elashstudioapp.ktx.showEditTextError

internal fun showInputLayoutError(
    editText: EditText,
    textInputLayout: TextInputLayout,
    message: String,
) {
    textInputLayout.setInputTextLayoutError(message)
    editText.showEditTextError()
}

internal fun hideInputLayoutError(editText: EditText, textInputLayout: TextInputLayout) {
    textInputLayout.hideInputTextLayoutError()
    editText.hideEditTextError()
}