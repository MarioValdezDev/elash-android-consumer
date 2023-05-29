package mx.mariovaldez.elashstudioapp.home.presentation.models

import android.graphics.drawable.Drawable

internal data class SectionUI(
    val id: String,
    val icon: Drawable?,
    val label: String,
    var isSelected: Boolean = false,
)
