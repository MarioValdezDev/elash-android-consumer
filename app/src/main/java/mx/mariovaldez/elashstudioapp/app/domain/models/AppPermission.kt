package mx.mariovaldez.elashstudioapp.app.domain.models

import android.Manifest

sealed class AppPermission(
    val name: String,
    val requestCode: Int
) {

    object AccessFineLocation : AppPermission(
        Manifest.permission.ACCESS_FINE_LOCATION,
        1001
    )
}
