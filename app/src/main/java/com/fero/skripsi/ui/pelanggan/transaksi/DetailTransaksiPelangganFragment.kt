package com.fero.skripsi.ui.pelanggan.transaksi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fero.skripsi.R


class DetailTransaksiPelangganFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_transaksi_pelanggan, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DetailTransaksiPelangganFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}