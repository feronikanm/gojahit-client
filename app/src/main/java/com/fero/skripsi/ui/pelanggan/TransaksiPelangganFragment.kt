package com.fero.skripsi.ui.pelanggan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fero.skripsi.databinding.FragmentTransaksiPelangganBinding

class TransaksiPelangganFragment : Fragment() {

    private lateinit var binding: FragmentTransaksiPelangganBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransaksiPelangganBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TransaksiPelangganFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}