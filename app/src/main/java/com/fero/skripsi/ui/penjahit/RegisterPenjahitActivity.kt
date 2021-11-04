package com.fero.skripsi.ui.penjahit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.fero.skripsi.databinding.ActivityRegisterPenjahitBinding
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.ui.main.HomePenjahitActivity
import com.fero.skripsi.ui.main.PilihUserActivity
import com.fero.skripsi.utils.ViewModelFactory

class RegisterPenjahitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterPenjahitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPenjahitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[AuthPenjahitViewModel::class.java]

        viewModel.apply {
            dataPenjahit.observe(this@RegisterPenjahitActivity, {
                val moveIntent = Intent(this@RegisterPenjahitActivity, HomePenjahitActivity::class.java)
                startActivity(moveIntent)
            })

            messageSuccess.observe(this@RegisterPenjahitActivity, {
                Toast.makeText(this@RegisterPenjahitActivity, it, Toast.LENGTH_SHORT).show()
            })

            showProgress.observe(this@RegisterPenjahitActivity, {
                if (it) {
                    // show progress
                } else {
                    // hide progress
                }
            })

            messageFailed.observe(this@RegisterPenjahitActivity, {
                Toast.makeText(this@RegisterPenjahitActivity, it, Toast.LENGTH_SHORT).show()
            })

        }

        binding.btnRegisPenjahit.setOnClickListener {

            val namaPenjahit = binding.etNamaPenjahit.text.toString().trim()
            val emailPenjahit = binding.etEmailPenjahit.text.toString().trim()
            val passwordPenjahit = binding.etPasswordPenjahit.text.toString().trim()

            val dataPenjahit = Penjahit(
                0,
                namaPenjahit,
                emailPenjahit,
                passwordPenjahit,
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",

                )

            viewModel.registerPenjahit(dataPenjahit)

//            Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
//            val moveIntent = Intent(this, HomePenjahitActivity::class.java)
//            startActivity(moveIntent)
        }

        binding.ivBack.setOnClickListener {
            val moveIntent = Intent(this, PilihUserActivity::class.java)
            startActivity(moveIntent)
        }

        binding.tvLogin.setOnClickListener {
            val moveIntent = Intent(this, LoginPenjahitActivity::class.java)
            startActivity(moveIntent)
        }
    }
}