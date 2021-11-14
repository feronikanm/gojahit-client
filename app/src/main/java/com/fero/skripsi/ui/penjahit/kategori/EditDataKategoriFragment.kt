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
import com.fero.skripsi.databinding.FragmentEditDataKategoriBinding
import com.fero.skripsi.model.ListDetailKategori
import com.fero.skripsi.ui.penjahit.kategori.viewmodel.KategoriPenjahitViewModel
import com.fero.skripsi.utils.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson


class EditDataKategoriFragment : DialogFragment() {

    private lateinit var binding: FragmentEditDataKategoriBinding
    val EXTRA_DETAIL_KATEGORI = "EXTRA_DETAIL_KATEGORI"
    val FIELD_REQUIRED = "Field tidak boleh kosong"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEditDataKategoriBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundleData = arguments?.getString(EXTRA_DETAIL_KATEGORI)
        val extraData = Gson().fromJson(bundleData, ListDetailKategori::class.java)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[KategoriPenjahitViewModel::class.java]

        viewModel.apply {
            dataListDetailKategori.observe(this@EditDataKategoriFragment, {
                dialog?.dismiss()
            })

            messageSuccess.observe(this@EditDataKategoriFragment, {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })

            messageFailed.observe(this@EditDataKategoriFragment, {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })
        }

        binding.tvNamaPenjahit.text = extraData.nama_penjahit
        binding.etKetKategori.setText(extraData.keterangan_kategori)
        binding.etBahanJahit.setText(extraData.bahan_jahit)
        binding.etHargaBahan.setText(extraData.harga_bahan)
        binding.etOngkosJahit.setText(extraData.ongkos_penjahit)
        binding.etLamaWaktu.setText(extraData.perkiraan_lama_waktu_pengerjaan)

        binding.btnSimpan.setOnClickListener {
            popupSimpanData(context, extraData)
        }

        binding.btnCancel.setOnClickListener {
            dialog?.cancel()
        }

    }

    private fun popupSimpanData(context: Context?, data: ListDetailKategori?) {
        val box: Context = ContextThemeWrapper(context, R.style.AppTheme)
        val materialAlertDialogBuilder = MaterialAlertDialogBuilder(box)
        materialAlertDialogBuilder.setTitle("Tambah Data")
            .setMessage("Apa anda yakin ingin mengubah data ini?")
            .setNegativeButton("Batalkan", null)
            .setPositiveButton("Tambah") { dialogInterface, i -> simpanData(data) }
            .show()
    }

    private fun simpanData(data: ListDetailKategori?) {

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

        val dataDetailKategori = ListDetailKategori(
            data?.id_detail_kategori,
            data?.id_penjahit,
            idKategori,
            data?.alamat_penjahit,
            bahanJahit,
            data?.email_penjahit,
            data?.foto_penjahit,
            data?.gambar_kategori,
            hargaBahan,
            data?.hari_buka,
            data?.jam_buka,
            data?.jam_tutup,
            data?.keterangan_toko,
            data?.jangkauan_kategori_penjahit,
            data?.spesifikasi_penjahit,
            lamaWaktu,
            ketKategori,
            data?.latitude_penjahit,
            data?.longitude_penjahit,
            data?.nama_kategori,
            data?.nama_penjahit,
            data?.nama_toko,
            ongkosPenjahit,
            data?.password_penjahit,
            data?.telp_penjahit,
        )
        viewModel.updateDataDetailKategori(dataDetailKategori)
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            EditDataKategoriFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}