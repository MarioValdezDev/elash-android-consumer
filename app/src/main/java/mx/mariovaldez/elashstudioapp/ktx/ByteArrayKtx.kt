package mx.mariovaldez.elashstudioapp.ktx

import android.util.Base64

fun ByteArray.encodeToString(): String = Base64.encodeToString(this, Base64.NO_WRAP)
