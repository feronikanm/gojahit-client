package com.fero.skripsi.ui.pelanggan.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.fero.skripsi.R
import com.fero.skripsi.databinding.FragmentDetailKategoriInPelangganBinding
import com.fero.skripsi.model.DetailKategoriNilai
import com.fero.skripsi.model.UkuranDetailKategori
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
            tvBahanJahit.text = "Bahan Jahit : " + data.bahan_jahit
            tvHargaBahan.text = "Harga Bahan : " + data.harga_bahan
            tvOngkosPenjahit.text = "Ongkos Penjahit : " + data.ongkos_penjahit
            tvLamaWaktu.text = "Lama Waktu : " + data.perkiraan_lama_waktu_pengerjaan
            tvKet.text = "Keterangan : \n" + data.keterangan_kategori

            Glide.with(this@DetailKategoriInPelangganFragment)
                .load("${Constant.IMAGE_KATEGORI}${data.gambar_kategori}")
                .into(imgKategori)
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