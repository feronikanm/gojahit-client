package com.fero.skripsi.ui.penjahit.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.fero.skripsi.databinding.ActivityLoginPenjahitBinding
import com.fero.skripsi.ui.main.HomePenjahitActivity
import com.fero.skripsi.ui.main.PilihUserActivity
import com.fero.skripsi.ui.penjahit.auth.viewmodel.AuthPenjahitViewModel
import com.fero.skripsi.utils.PrefHelper
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_ALAMAT_PENJAHIT
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_EMAIL_PENJAHIT
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_FOTO_PENJAHIT
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_HARI_BUKA_PENJAHIT
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_ID_PENJAHIT
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_IS_LOGIN_PENJAHIT
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_JAM_BUKA_PENJAHIT
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_JAM_TUTUP_PENJAHIT
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_JANGKAUAN_PENJAHIT
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_KET_PENJAHIT
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_LATITUDE_PENJAHIT
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_LONGITUDE_PENJAHIT
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_NAMA_PENJAHIT
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_NAMA_TOKO_PENJAHIT
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_PASSWORD_PENJAHIT
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_SPESIFIKASI_PENJAHIT
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_TELP_PENJAHIT
import com.fero.skripsi.utils.ViewModelFactory

class LoginPenjahitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginPenjahitBinding
    lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPenjahitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefHelper = PrefHelper(this)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[AuthPenjahitViewModel::class.java]

        viewModel.apply{

            dataPenjahit.observe(this@LoginPenjahitActivity, {

                if(prefHelper.getBoolean(PREF_IS_LOGIN_PENJAHIT)){

                    val idPenjahit = it.id_penjahit.toString()
                    val namaPenjahit = it.nama_penjahit

                    prefHelper.put(PREF_ID_PENJAHIT, idPenjahit)
                    prefHelper.put(PREF_NAMA_PENJAHIT, namaPenjahit!!)

                    val moveIntent = Intent(this@LoginPenjahitActivity, HomePenjahitActivity::class.java)
                    moveIntent.putExtra("EXTRA_LOGIN_PENJAHIT", it)
                    startActivity(moveIntent)

                }
            })

            messageSuccess.observe(this@LoginPenjahitActivity, {
                Log.d("PREF_IS_LOGIN_PENJAHIT", "Value is : ${prefHelper.getBoolean(PREF_IS_LOGIN_PENJAHIT)}")
                Toast.makeText(this@LoginPenjahitActivity, it, Toast.LENGTH_SHORT).show()
            })

            showProgress.observe(this@LoginPenjahitActivity, {
                if (it) {
                    // show progress
                } else {
                    // hide progress
                }
            })

            messageFailed.observe(this@LoginPenjahitActivity, {
                Toast.makeText(this@LoginPenjahitActivity, it, Toast.LENGTH_SHORT).show()
            })
        }

        binding.btnLoginPenjahit.setOnClickListener {

            val emailPenjahit = binding.etEmailPenjahit.text.toString().trim()
            val passwordPenjahit = binding.etPasswordPenjahit.text.toString().trim()

            saveSession(emailPenjahit, passwordPenjahit)
            viewModel.loginPenjahit(emailPenjahit, passwordPenjahit)
        }

        binding.ivBack.setOnClickListener {
            val moveIntent = Intent(this, PilihUserActivity::class.java)
            startActivity(moveIntent)
        }

        binding.tvRegister.setOnClickListener {
            val moveIntent = Intent(this, RegisterPenjahitActivity::class.java)
            startActivity(moveIntent)
        }
    }

    private fun saveSession(emailPenjahit: String, passwordPenjahit: String) {
        prefHelper.put(PREF_EMAIL_PENJAHIT, emailPenjahit)
        prefHelper.put(PREF_PASSWORD_PENJAHIT, passwordPenjahit)
        prefHelper.put(PREF_IS_LOGIN_PENJAHIT, true)
    }
}