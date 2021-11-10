package com.fero.skripsi.ui.penjahit.kategori

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fero.skripsi.R
import com.fero.skripsi.databinding.FragmentEditDataKategoriBinding


class EditDataKategoriFragment : Fragment() {

    private lateinit var binding: FragmentEditDataKategoriBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditDataKategoriBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    companion object {
        @JvmStatic
        fun newInstance() =
            EditDataKategoriFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}