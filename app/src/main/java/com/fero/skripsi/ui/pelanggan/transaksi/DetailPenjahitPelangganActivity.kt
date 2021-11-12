package com.fero.skripsi.ui.pelanggan.transaksi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.fero.skripsi.R
import com.fero.skripsi.databinding.ActivityDetailPenjahitPelangganBinding
import com.fero.skripsi.databinding.ContentDataPenjahitBinding
import com.fero.skripsi.model.DetailKategoriNilai
import com.fero.skripsi.model.Nilai
import com.fero.skripsi.utils.Constant

class DetailPenjahitPelangganActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPenjahitPelangganBinding
    private lateinit var contentBinding: ContentDataPenjahitBinding

    companion object {
        const val EXTRA_DATA_PENJAHIT = "EXTRA_DATA_PENJAHIT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPenjahitPelangganBinding.inflate(layoutInflater)
        contentBinding = binding.detailPenjahit
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.collapsingToolbar.setTitle("Data Penjahit")
        toolbarTextAppernce()

        val extraData: DetailKategoriNilai? = intent.extras?.getParcelable(EXTRA_DATA_PENJAHIT)

        binding.apply {
            if (extraData != null) {
                tvNamaToko.text = extraData.nama_toko
                tvNamaPenjahit.text = extraData.nama_penjahit

                Glide.with(this@DetailPenjahitPelangganActivity)
                    .load("${Constant.IMAGE_PENJAHIT}${extraData.foto_penjahit}")
                    .into(imgPenjahit)
            }

        }

        contentBinding.apply {
            tvNamaPenjahit.text = extraData!!.nama_penjahit
            tvEmailPenjahit.text = extraData.email_penjahit
            tvTeleponPenjahit.text = extraData.telp_penjahit
            tvAlamatPenjahit.text = extraData.alamat_penjahit

            btnLakukanJahit.setOnClickListener {
                Toast.makeText(this@DetailPenjahitPelangganActivity, "Melakukan Jahitan" , Toast.LENGTH_SHORT).show()
//                val moveIntent = Intent(this@DetailPenjahitPelangganActivity, PilihUserActivity::class.java)
//                startActivity(moveIntent)
            }

        }


    }


    private fun toolbarTextAppernce() {
        binding.collapsingToolbar.setCollapsedTitleTextAppearance(R.style.collapsedappbar)
        binding.collapsingToolbar.setExpandedTitleTextAppearance(R.style.expandedappbar)
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