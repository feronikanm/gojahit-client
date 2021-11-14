package com.fero.skripsi.ui.penjahit.ukuran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.fero.skripsi.databinding.ActivityUkuranDetailKategoriBinding
import com.fero.skripsi.model.ListDetailKategori
import com.fero.skripsi.model.UkuranDetailKategori
import com.fero.skripsi.ui.penjahit.ukuran.adapter.UkuranDetailKategoriAdapter
import com.fero.skripsi.ui.penjahit.ukuran.viewmodel.UkuranViewModel
import com.fero.skripsi.utils.Constant
import com.fero.skripsi.utils.ViewModelFactory

class UkuranDetailKategoriActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUkuranDetailKategoriBinding
    companion object {
        const val EXTRA_DATA_KATEGORI = "EXTRA_DATA_KATEGORI"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUkuranDetailKategoriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extraData: ListDetailKategori? = intent.extras?.getParcelable(EXTRA_DATA_KATEGORI)

        binding.apply {
            tvNamaKategori.text = extraData!!.nama_kategori
            tvKetKategori.text = extraData.keterangan_kategori
            tvBahanJahit.text = extraData.bahan_jahit
            tvHargaBahan.text = extraData.harga_bahan
            tvOngkosPenjahit.text = extraData.ongkos_penjahit
            tvLamaWaktu.text = extraData.perkiraan_lama_waktu_pengerjaan

            Glide.with(this@UkuranDetailKategoriActivity)
                .load("${Constant.IMAGE_KATEGORI}${extraData.gambar_kategori}")
                .into(imgKategori)
        }

        val dataUkuran = UkuranDetailKategori(
            0,
            extraData?.id_detail_kategori,
            0,
            0,
            0,
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

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[UkuranViewModel::class.java]

        viewModel.apply {
            listUkuran.observe(this@UkuranDetailKategoriActivity, {
                setupRvUkuranDetailKategori(it)
            })

            messageSuccess.observe(this@UkuranDetailKategoriActivity, {
                Toast.makeText(this@UkuranDetailKategoriActivity, it, Toast.LENGTH_SHORT).show()
            })

            eventShowProgress.observe(this@UkuranDetailKategoriActivity, {
                if (it) {
                    // show progress
                } else {
                    // hide progress
                }
            })

            messageFailed.observe(this@UkuranDetailKategoriActivity, {
                Toast.makeText(this@UkuranDetailKategoriActivity, it, Toast.LENGTH_SHORT).show()
            })
        }

        viewModel.getUkuranByDetailKategori(dataUkuran)

    }

    private fun setupRvUkuranDetailKategori(data: List<UkuranDetailKategori>?) {
        val ukuranAdapter = UkuranDetailKategoriAdapter()
        ukuranAdapter.setUkuranDetailKategori(data!!)

        binding.rvUkuran.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ukuranAdapter
        }
    }
}