package mx.mariovaldez.elashstudioapp.ktx

import com.google.android.material.textfield.TextInputLayout

internal fun TextInputLayout.setInputTextLayoutError(string: String) {
    error = string
    errorIconDrawable = null
}

internal fun TextInputLayout.hideInputTextLayoutError() {
    error = null
}