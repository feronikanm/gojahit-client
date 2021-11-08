package com.fero.skripsi.ui.penjahit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fero.skripsi.R
import com.fero.skripsi.databinding.FragmentKategoriPenjahitBinding

class KategoriPenjahitFragment : Fragment() {

    private lateinit var binding: FragmentKategoriPenjahitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentKategoriPenjahitBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            KategoriPenjahitFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}