package mx.mariovaldez.elashstudioapp.ui

import androidx.annotation.StringRes
import mx.mariovaldez.elashstudioapp.databinding.LayoutToolbarBinding
import mx.mariovaldez.elashstudioapp.ktx.context

internal fun LayoutToolbarBinding.bind(
    @StringRes titleId: Int? = null,
    title: String? = null,
    onBackPressed: () -> Unit,
) {
    titleTextView.text = title ?: titleId?.let { context.getString(it) } ?: ""
    backButton.setOnClickListener { onBackPressed() }
}
