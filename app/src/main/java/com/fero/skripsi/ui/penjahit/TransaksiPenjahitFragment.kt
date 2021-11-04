package com.fero.skripsi.ui.penjahit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fero.skripsi.core.BaseFragment
import com.fero.skripsi.databinding.FragmentTransaksiPenjahitBinding

class TransaksiPenjahitFragment : BaseFragment<FragmentTransaksiPenjahitBinding>() {

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTransaksiPenjahitBinding {
        return FragmentTransaksiPenjahitBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
        // TODO("Not yet implemented")
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        // TODO("Not yet implemented")
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TransaksiPenjahitFragment().apply {
                arguments = Bundle().apply {}
            }
    }


}