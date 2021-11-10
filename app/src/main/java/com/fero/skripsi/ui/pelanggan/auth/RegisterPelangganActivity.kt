package com.fero.skripsi.ui.pelanggan.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.fero.skripsi.databinding.ActivityRegisterPelangganBinding
import com.fero.skripsi.model.Pelanggan
import com.fero.skripsi.ui.main.HomePelangganActivity
import com.fero.skripsi.ui.main.PilihUserActivity
import com.fero.skripsi.utils.ViewModelFactory

class RegisterPelangganActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterPelangganBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPelangganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[AuthPelangganViewModel::class.java]

        viewModel.apply {
            dataPelanggan.observe(this@RegisterPelangganActivity, {
                val moveIntent = Intent(this@RegisterPelangganActivity, HomePelangganActivity::class.java)
//                intent.putExtra("EXTRA_REGISTER", it)
                startActivity(moveIntent)
            })

            messageSuccess.observe(this@RegisterPelangganActivity, {
                Toast.makeText(this@RegisterPelangganActivity, it, Toast.LENGTH_SHORT).show()
            })

            showProgress.observe(this@RegisterPelangganActivity, {
                if (it) {
                    // show progress
                } else {
                    // hide progress
                }
            })

            messageFailed.observe(this@RegisterPelangganActivity, {
                Toast.makeText(this@RegisterPelangganActivity, it, Toast.LENGTH_SHORT).show()
            })

        }

        binding.btnRegisterPelanggan.setOnClickListener {

            val namaPelanggan = binding.etNamaPelanggan.text.toString().trim()
            val emailPelanggan = binding.etEmailPelanggan.text.toString().trim()
            val passwordPelanggan = binding.etPasswordPelanggan.text.toString().trim()

            val dataPelangan = Pelanggan(
                0,
                namaPelanggan,
                emailPelanggan,
                passwordPelanggan,
                "",
                "",
                "",
                "",
                "",
                "",
            )

            viewModel.registerPelanggan(dataPelangan)
        }

        binding.ivBack.setOnClickListener {
            val moveIntent = Intent(this, PilihUserActivity::class.java)
            startActivity(moveIntent)
        }

        binding.tvLogin.setOnClickListener {
            val moveIntent = Intent(this, LoginPelangganActivity::class.java)
            startActivity(moveIntent)
        }

    }

}