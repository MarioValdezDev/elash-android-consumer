package mx.mariovaldez.elashstudioapp.home.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.mariovaldez.elashstudioapp.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }


    companion object {

        fun launch(from: Context) = from.startActivity(Intent(from, HomeActivity::class.java))
    }
}