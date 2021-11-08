package com.fero.skripsi.ui.penjahit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fero.skripsi.core.BaseFragment
import com.fero.skripsi.databinding.FragmentTransaksiPenjahitBinding
import com.fero.skripsi.utils.FunctionKu

class TransaksiPenjahitFragment : BaseFragment<FragmentTransaksiPenjahitBinding>() {

    val angka = 5

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTransaksiPenjahitBinding {
        return FragmentTransaksiPenjahitBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
        // TODO("Not yet implemented")
    }

    override fun setupUI(view: View, savedInstanceState: Bundle?) {
        // TODO("Not yet implemented")
        Log.d("Hasil Penjumlahan", FunctionKu.getHasilPenjumlahan(angka).toString())
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TransaksiPenjahitFragment().apply {
                arguments = Bundle().apply {}
            }
    }


}