package com.fero.skripsi.ui.pelanggan.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.fero.skripsi.R
import com.fero.skripsi.databinding.FragmentDetailKategoriInPelangganBinding
import com.fero.skripsi.model.DetailKategoriNilai
import com.fero.skripsi.model.UkuranDetailKategori
import com.fero.skripsi.ui.pelanggan.auth.EditDataPelangganActivity
import com.fero.skripsi.ui.pelanggan.pesanan.PesananActivity
import com.fero.skripsi.ui.pelanggan.pesanan.PesananActivity.Companion.EXTRA_DATA_DETAIL_KATEGORI_NILAI
import com.fero.skripsi.utils.Constant
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_detail_kategori_in_pelanggan.*

class DetailKategoriInPelangganFragment : DialogFragment() {

    private lateinit var binding: FragmentDetailKategoriInPelangganBinding
    val EXTRA_DETAIL_KATEGORI = "EXTRA_DETAIL_KATEGORI"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailKategoriInPelangganBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundleData = arguments?.getString(EXTRA_DETAIL_KATEGORI)
        val data = Gson().fromJson(bundleData, DetailKategoriNilai::class.java)

        binding.apply {

            tvNamaKategori.text = data.nama_kategori
            tvNamaPenjahit.text = data.nama_penjahit
            tvIdPenjahit.text = data.id_penjahit.toString()
            tvBahanJahit.text = data.bahan_jahit
            tvHargaBahan.text = data.harga_bahan.toString()
            tvOngkosPenjahit.text = data.ongkos_penjahit.toString()
            tvLamaWaktu.text = data.perkiraan_lama_waktu_pengerjaan
            tvKet.text = data.keterangan_kategori

//            Glide.with(this@DetailKategoriInPelangganFragment)
//                .load("${Constant.IMAGE_KATEGORI}${data.gambar_kategori}")
//                .into(imgKategori)
        }

        binding.btnLakukanJahit.setOnClickListener {
            Toast.makeText(context, "Melakukan Pesanan" , Toast.LENGTH_SHORT).show()
            val moveIntent = Intent(context, PesananActivity::class.java)
            moveIntent.putExtra(EXTRA_DATA_DETAIL_KATEGORI_NILAI, data)
            startActivity(moveIntent)
        }

        binding.btnClose.setOnClickListener {
            dialog?.cancel()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DetailKategoriInPelangganFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}