package com.fero.skripsi.ui.pelanggan.transaksi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fero.skripsi.databinding.FragmentTransaksiPelangganBinding
import com.fero.skripsi.model.Pelanggan
import com.google.gson.Gson

class TransaksiPelangganFragment : Fragment() {

    private lateinit var binding: FragmentTransaksiPelangganBinding
    val EXTRA_PELANGGAN = "EXTRA_PELANGGAN"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransaksiPelangganBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundleData = arguments?.getString(EXTRA_PELANGGAN)
        val dataPelanggan = Gson().fromJson(bundleData, Pelanggan::class.java)

        binding.tvNamaPelanggan.text = "Halo " + dataPelanggan.nama_pelanggan.toString()

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TransaksiPelangganFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}