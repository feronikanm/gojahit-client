package com.fero.skripsi.ui.penjahit.transaksi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fero.skripsi.R


class DetailTransaksiPenjahitFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_transaksi_penjahit, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DetailTransaksiPenjahitFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}