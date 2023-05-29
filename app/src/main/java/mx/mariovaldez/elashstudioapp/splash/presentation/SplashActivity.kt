package mx.mariovaldez.elashstudioapp.splash.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.mariovaldez.elashstudioapp.login.presentation.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            delay(LAUNCH_DELAY)
            launchLogin()
        }
    }

    private fun launchLogin() {
        LoginActivity.launch(this)
        finish()
    }

    companion object {

        private const val LAUNCH_DELAY: Long = 1700
    }
}
