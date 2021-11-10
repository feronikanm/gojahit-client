package com.fero.skripsi.ui.penjahit.transaksi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.fero.skripsi.databinding.ActivityDetailPenjahitBinding
import com.fero.skripsi.model.Nilai
import com.fero.skripsi.utils.Constant

class DetailPenjahitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPenjahitBinding

    companion object {
        const val EXTRA_PENJAHIT = "EXTRA_PENJAHIT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPenjahitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Data Penjahit"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Using Gson
//        val extraData : String = intent.extras?.getString(EXTRA_PENJAHIT)!!
//        val dataNilai = Gson().fromJson(extraData, Nilai::class.java)
//        val penjahitId = dataNilai.id_penjahit

        // Using Parcelable
        val extraData: Nilai? = intent.extras?.getParcelable(EXTRA_PENJAHIT)

        binding.progressBar.visibility = View.VISIBLE
        binding.content.visibility = View.INVISIBLE

        binding.apply {

            binding.progressBar.visibility = View.GONE
            binding.content.visibility = View.VISIBLE

            if (extraData != null) {
                tvNamaToko.text = extraData.nama_toko
                tvNamaPenjahit.text = extraData.nama_penjahit

                Glide.with(this@DetailPenjahitActivity)
                    .load("${Constant.IMAGE_PENJAHIT}${extraData.foto_penjahit}")
                    .into(imgPenjahit)
            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}