package com.fero.skripsi.ui.penjahit.kategori

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.fero.skripsi.R
import com.fero.skripsi.databinding.FragmentTambahDataKategoriBinding

class TambahDataKategoriFragment : DialogFragment() {

    private lateinit var binding: FragmentTambahDataKategoriBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentTambahDataKategoriBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSimpan.setOnClickListener {
            var value = 2
            when (binding.rgKategori.checkedRadioButtonId) {
                R.id.rb_kategori_2 -> value = 2
                R.id.rb_kategori_3 -> value = 3
                R.id.rb_kategori_4 -> value = 4
                R.id.rb_kategori_5 -> value = 5
                R.id.rb_kategori_6 -> value = 6
                R.id.rb_kategori_7 -> value = 7
                R.id.rb_kategori_8 -> value = 8
                R.id.rb_kategori_9 -> value = 9
            }
            val idKategori = value
        }

        binding.btnCancel.setOnClickListener {
            dialog?.cancel()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TambahDataKategoriFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}