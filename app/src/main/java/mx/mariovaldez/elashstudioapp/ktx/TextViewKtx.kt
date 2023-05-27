package mx.mariovaldez.elashstudioapp.ktx

import android.widget.TextView
import androidx.core.text.HtmlCompat

internal fun TextView.setHtml(
    html: String,
) {
    text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)
}
