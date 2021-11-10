package com.fero.skripsi.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fero.skripsi.databinding.ActivityPilihUserBinding
import com.fero.skripsi.ui.pelanggan.auth.LoginPelangganActivity
import com.fero.skripsi.ui.penjahit.auth.LoginPenjahitActivity

class PilihUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPilihUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPilihUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Pilih Salah Satu"

        binding.layoutUser1.setOnClickListener {
            val moveIntent = Intent(this, LoginPelangganActivity::class.java)
            startActivity(moveIntent)
        }

        binding.layoutUser2.setOnClickListener {
            val moveIntent = Intent(this, LoginPenjahitActivity::class.java)
            startActivity(moveIntent)
        }



    }
}