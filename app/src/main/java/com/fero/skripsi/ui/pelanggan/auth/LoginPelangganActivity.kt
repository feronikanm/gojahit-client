package com.fero.skripsi.ui.pelanggan.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.fero.skripsi.databinding.ActivityLoginPelangganBinding
import com.fero.skripsi.ui.main.HomePelangganActivity
import com.fero.skripsi.ui.main.PilihUserActivity
import com.fero.skripsi.ui.pelanggan.auth.viewmodel.AuthPelangganViewModel
import com.fero.skripsi.utils.PrefHelper
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_ALAMAT_PELANGGAN
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_EMAIL_PELANGGAN
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_FOTO_PELANGGAN
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_ID_PELANGGAN
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_IS_LOGIN_PELANGGAN
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_JK_PELANGGAN
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_LATITUDE_PELANGGAN
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_LONGITUDE_PELANGGAN
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_NAMA_PELANGGAN
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_PASSWORD_PELANGGAN
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_TELP_PELANGGAN
import com.fero.skripsi.utils.ViewModelFactory

class LoginPelangganActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginPelangganBinding
    lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPelangganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefHelper = PrefHelper(this)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[AuthPelangganViewModel::class.java]

        viewModel.apply{

            dataPelanggan.observe(this@LoginPelangganActivity, {
//                onStart()
                if (prefHelper.getBoolean(PREF_IS_LOGIN_PELANGGAN)) {

                    val idPelanggan = it.id_pelanggan.toString()
                    val namaPelanggan = it.nama_pelanggan
                    val teleponPelanggan = it.telp_pelanggan
                    val latPelanggan = it.latitude_pelanggan
                    val longPelanggan = it.longitude_pelanggan
                    val alamatPelanggan = it.alamat_pelanggan
                    val jkPelanggan = it.jk_pelanggan
                    val fotoPelanggan = it.foto_pelanggan

                    prefHelper.put(PREF_ID_PELANGGAN, idPelanggan)
                    prefHelper.put(PREF_NAMA_PELANGGAN, namaPelanggan!!)
                    prefHelper.put(PREF_TELP_PELANGGAN, teleponPelanggan!!)
                    prefHelper.put(PREF_LATITUDE_PELANGGAN, latPelanggan!!)
                    prefHelper.put(PREF_LONGITUDE_PELANGGAN, longPelanggan!!)
                    prefHelper.put(PREF_ALAMAT_PELANGGAN, alamatPelanggan!!)
                    prefHelper.put(PREF_JK_PELANGGAN, jkPelanggan!!)
                    prefHelper.put(PREF_FOTO_PELANGGAN, fotoPelanggan!!)

                    val moveIntent = Intent(this@LoginPelangganActivity, HomePelangganActivity::class.java)
                    moveIntent.putExtra("EXTRA_LOGIN_PELANGGAN", it)
                    startActivity(moveIntent)
                }
            })

            messageSuccess.observe(this@LoginPelangganActivity, {
                Toast.makeText(this@LoginPelangganActivity, it, Toast.LENGTH_SHORT).show()
            })

            showProgress.observe(this@LoginPelangganActivity, {
                if (it) {
                    // show progress
                } else {
                    // hide progress
                }
            })

            messageFailed.observe(this@LoginPelangganActivity, {
                Toast.makeText(this@LoginPelangganActivity, it, Toast.LENGTH_SHORT).show()
            })
        }

        binding.btnLoginPelanggan.setOnClickListener {

            val emailPelanggan = binding.etEmailPelanggan.text.toString().trim()
            val passwordPelanggan = binding.etPasswordPelanggan.text.toString().trim()

            saveSession(emailPelanggan, passwordPelanggan)
            viewModel.loginPelanggan(emailPelanggan, passwordPelanggan)
        }

        binding.ivBack.setOnClickListener {
            val moveIntent = Intent(this, PilihUserActivity::class.java)
            startActivity(moveIntent)
        }

        binding.tvRegister.setOnClickListener {
            val moveIntent = Intent(this, RegisterPelangganActivity::class.java)
            startActivity(moveIntent)
        }
    }

    private fun saveSession(emailPelanggan: String, passwordPelanggan: String) {
        prefHelper.put(PREF_EMAIL_PELANGGAN, emailPelanggan)
        prefHelper.put(PREF_PASSWORD_PELANGGAN, passwordPelanggan)
        prefHelper.put(PREF_IS_LOGIN_PELANGGAN, true)
    }
}