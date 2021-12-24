package com.fero.skripsi.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.fero.skripsi.R
import com.fero.skripsi.ui.landingpage.IntroActivity
import com.fero.skripsi.utils.FunctionKu

class SplashScreenActivity : AppCompatActivity() {

    val angka = 1

    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Log.d("Hasil Penjumlahan", FunctionKu.getHasilPenjumlahan(angka).toString())

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, IntroActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }, 2000)
    }
}