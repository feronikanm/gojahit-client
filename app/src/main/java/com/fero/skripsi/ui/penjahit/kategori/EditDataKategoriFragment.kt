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
import com.fero.skripsi.model.DetailKategoriPenjahit
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
        val extraData = Gson().fromJson(bundleData, DetailKategoriPenjahit::class.java)

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

    private fun popupSimpanData(context: Context?, data: DetailKategoriPenjahit?) {
        val box: Context = ContextThemeWrapper(context, R.style.AppTheme)
        val materialAlertDialogBuilder = MaterialAlertDialogBuilder(box)
        materialAlertDialogBuilder.setTitle("Perbarui Data")
            .setMessage("Apa Anda yakin ingin mengubah data ini?")
            .setNegativeButton("Batalkan", null)
            .setPositiveButton("Simpan") { dialogInterface, i -> simpanData(data) }
            .show()
    }

    private fun simpanData(data: DetailKategoriPenjahit?) {

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[KategoriPenjahitViewModel::class.java]

        val ketKategori = binding.etKetKategori.text.toString().trim()
        val bahanJahit = binding.etBahanJahit.text.toString().trim()
        val hargaBahan = binding.etHargaBahan.text.toString().trim()
        val ongkosPenjahit = binding.etOngkosJahit.text.toString().trim()
        val lamaWaktu = binding.etLamaWaktu.text.toString().trim()

        if (ketKategori.isEmpty()){
            binding.etKetKategori.error = FIELD_REQUIRED
            return
        }

        val dataDetailKategori = DetailKategoriPenjahit(
            data?.id_detail_kategori,
            data?.id_penjahit,
            data?.id_kategori,
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