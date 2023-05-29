package mx.mariovaldez.elashstudioapp.ktx

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import mx.mariovaldez.elashstudioapp.app.domain.models.AppPermission

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) {
    bindingInflater.invoke(layoutInflater)
}

fun AppCompatActivity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun AppCompatActivity.copyToClipboard(text: String) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.setPrimaryClip(ClipData.newPlainText("", text))
}

fun AppCompatActivity.isAppPermissionGranted(permission: AppPermission): Boolean =
    ContextCompat.checkSelfPermission(this, permission.name) ==
        PackageManager.PERMISSION_GRANTED

fun AppCompatActivity.shouldShowRequestAppPermissionRationale(permission: AppPermission): Boolean =
    shouldShowRequestPermissionRationale(permission.name)

fun AppCompatActivity.registerActionsForAppPermissionRequest(
    onGuaranteed: () -> Unit,
    onDenied: () -> Unit
): ActivityResultLauncher<String> = registerForActivityResult(RequestPermission()) { isGranted ->
    if (isGranted) onGuaranteed() else onDenied()
}

fun AppCompatActivity.launchAppPermissionRequest(
    requestPermissionLauncher: ActivityResultLauncher<String>,
    permission: AppPermission
) = requestPermissionLauncher.launch(permission.name)

fun AppCompatActivity.requestAppPermissions(
    vararg appPermission: AppPermission,
    requestCode: Int
) = requestPermissions(appPermission.map { it.name }.toTypedArray(), requestCode)

fun AppCompatActivity.clearAnimationTransition(): Unit = overridePendingTransition(0, 0)

fun AppCompatActivity.shareImageFromUri(uri: Uri) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        type = "image/png"
    }
    startActivity(intent)
}
