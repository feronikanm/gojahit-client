package com.fero.skripsi.ui.penjahit.kategori

import android.content.Context
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.fero.skripsi.R
import com.fero.skripsi.databinding.FragmentTambahDataKategoriBinding
import com.fero.skripsi.model.DetailKategori
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.utils.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_penjahit.*

class TambahDataKategoriFragment : DialogFragment() {

    private lateinit var binding: FragmentTambahDataKategoriBinding
    val EXTRA_PENJAHIT = "EXTRA_PENJAHIT"
    val FIELD_REQUIRED = "Field tidak boleh kosong"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentTambahDataKategoriBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundleData = arguments?.getString(EXTRA_PENJAHIT)
        val dataPenjahit = Gson().fromJson(bundleData, Penjahit::class.java)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[KategoriPenjahitViewModel::class.java]

        viewModel.apply {
            dataDetailKategori.observe(this@TambahDataKategoriFragment, {
                dialog?.dismiss()
            })

            messageSuccess.observe(this@TambahDataKategoriFragment, {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })

            messageFailed.observe(this@TambahDataKategoriFragment, {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })
        }

        binding.tvNamaPenjahit.text = dataPenjahit.nama_penjahit

        binding.btnSimpan.setOnClickListener {
            popupSimpanData(context, dataPenjahit)
        }

        binding.btnCancel.setOnClickListener {
            dialog?.cancel()
        }
    }

    private fun popupSimpanData(context: Context?, dataPenjahit: Penjahit?) {
        val box: Context = ContextThemeWrapper(context, R.style.AppTheme)
        val materialAlertDialogBuilder = MaterialAlertDialogBuilder(box)
        materialAlertDialogBuilder.setTitle("Tambah Data")
            .setMessage("Apa anda yakin ingin menambah data ini?")
            .setNegativeButton("Batalkan", null)
            .setPositiveButton("Tambah") { dialogInterface, i -> simpanData(dataPenjahit) }
            .show()
    }


    private fun simpanData(dataPenjahit: Penjahit?) {

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[KategoriPenjahitViewModel::class.java]

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

        val ketKategori = binding.etKetKategori.text.toString().trim()
        val bahanJahit = binding.etBahanJahit.text.toString().trim()
        val hargaBahan = binding.etBahanJahit.text.toString().trim()
        val ongkosPenjahit = binding.etOngkosJahit.text.toString().trim()
        val lamaWaktu = binding.etLamaWaktu.text.toString().trim()

        if (ketKategori.isEmpty()){
            binding.etKetKategori.error = FIELD_REQUIRED
            return
        }

        val dataDetailKategori = DetailKategori(
            0,
            dataPenjahit?.id_penjahit,
            idKategori,
            ketKategori,
            bahanJahit,
            hargaBahan,
            ongkosPenjahit,
            lamaWaktu,
        )

        viewModel.insertDataDetailKategori(dataDetailKategori)
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