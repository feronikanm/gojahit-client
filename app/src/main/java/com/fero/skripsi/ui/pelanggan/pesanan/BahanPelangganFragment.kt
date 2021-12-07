package com.fero.skripsi.ui.pelanggan.pesanan

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fero.skripsi.R
import com.fero.skripsi.databinding.FragmentBahanPelangganBinding
import com.fero.skripsi.model.DetailKategoriNilai
import com.fero.skripsi.model.Pelanggan
import com.fero.skripsi.model.Pesanan
import com.fero.skripsi.ui.main.HomePelangganActivity
import com.fero.skripsi.ui.pelanggan.pesanan.viewmodel.PesananViewModel
import com.fero.skripsi.ui.penjahit.kategori.viewmodel.KategoriPenjahitViewModel
import com.fero.skripsi.utils.PrefHelper
import com.fero.skripsi.utils.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class BahanPelangganFragment : Fragment() {

    private lateinit var binding: FragmentBahanPelangganBinding
    val EXTRA_DATA_BAHAN_PELANGGAN = "EXTRA_DATA_BAHAN_PELANGGAN"
    lateinit var prefHelper: PrefHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBahanPelangganBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefHelper = PrefHelper(context)
        val idPelanggan = prefHelper.getString(PrefHelper.PREF_ID_PELANGGAN)
        val idPelangganInt : Int = idPelanggan!!.toInt()
        val namaPelanggan = prefHelper.getString(PrefHelper.PREF_NAMA_PELANGGAN)
        val emailPelanggan = prefHelper.getString(PrefHelper.PREF_EMAIL_PELANGGAN)
        val passwordPelanggan = prefHelper.getString(PrefHelper.PREF_PASSWORD_PELANGGAN)
        val telpPelanggan = prefHelper.getString(PrefHelper.PREF_TELP_PELANGGAN)
        val latPelanggan = prefHelper.getString(PrefHelper.PREF_LATITUDE_PELANGGAN)
        val longPelanggan = prefHelper.getString(PrefHelper.PREF_LONGITUDE_PELANGGAN)
        val alamatPelanggan = prefHelper.getString(PrefHelper.PREF_ALAMAT_PELANGGAN)
        val jkPelanggan = prefHelper.getString(PrefHelper.PREF_JK_PELANGGAN)
        val fotoPelanggan = prefHelper.getString(PrefHelper.PREF_FOTO_PELANGGAN)

        val dataPelanggan = Pelanggan(
            idPelangganInt,
            namaPelanggan,
            emailPelanggan,
            passwordPelanggan,
            alamatPelanggan,
            jkPelanggan,
            latPelanggan,
            longPelanggan,
            telpPelanggan,
            fotoPelanggan,
        )

        val bundleData = arguments?.getString(EXTRA_DATA_BAHAN_PELANGGAN)
        val data = Gson().fromJson(bundleData, DetailKategoriNilai::class.java)
        Log.d("Data : ", data.toString())

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[PesananViewModel::class.java]

        viewModel.apply {
            dataPesanan.observe(this@BahanPelangganFragment, {
                val move = Intent(requireContext(), HomePelangganActivity::class.java)
                move.putExtra("EXTRA_LOGIN_PELANGGAN", dataPelanggan)
                startActivity(move)
            })

            messageSuccess.observe(this@BahanPelangganFragment, {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })

            messageFailed.observe(this@BahanPelangganFragment, {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })
        }

        val sdf = SimpleDateFormat("yyyy-M-dd")
        val currentDate = sdf.format(Date())
        binding.tvTanggalSelesai.text = currentDate

        binding.tvIdPenjahit.text = "id Penjahit : " + data.id_penjahit.toString()
        binding.tvNamaPenjahit.text = "Nama Penjahit : " + data!!.nama_penjahit
        binding.tvIdKategori.text = "id kategori : " + data.id_kategori.toString()
        binding.tvNamaKategori.text = "nama kategori : " + data.nama_kategori
        binding.tvIdDetailKategori.text = "id detail kategori : " + data.id_detail_kategori.toString()
        binding.tvIdPelanggan.text = "id pelanggan : " + idPelanggan
        binding.tvNamaPelanggan.text = "nama pelanggan : " + namaPelanggan
        binding.tvOngkosPenjahit.text = "ongkos penjahit : " + data.ongkos_penjahit
        binding.tvTotalBiaya.text = "total biaya = ongkos x jumlah = "
        binding.tvStatusBahan.text = "Belum di verifikasi"

        binding.btnPilihTanggal.setOnClickListener {

            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(requireActivity(), DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                // Display Selected date in TextView
                val stringDate = "$year-${month+1}-$dayOfMonth"
                binding.tvTanggalSelesai.text = stringDate
            }, year, month, day)
            dpd.show()
        }

        binding.btnKirimData.setOnClickListener {
            popupKirimData(context, data)
        }
    }

    private fun popupKirimData(context: Context?, data: DetailKategoriNilai) {
        val box: Context = ContextThemeWrapper(context, R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
        val materialAlertDialogBuilder = MaterialAlertDialogBuilder(box)
        materialAlertDialogBuilder.setTitle("Kirim Data")
            .setMessage("Apa Anda sudah yakin ingin melakukan jahitan ini?")
            .setNegativeButton("Batalkan", null)
            .setPositiveButton("Kirim") { dialogInterface, i -> kirimData(data) }
            .show()
    }

    private fun kirimData(data: DetailKategoriNilai) {
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[PesananViewModel::class.java]

        prefHelper = PrefHelper(context)
        val idPelangganPH = prefHelper.getString(PrefHelper.PREF_ID_PELANGGAN)

        val idPelanggan : Int = idPelangganPH!!.toInt()

        var value : String? = ""
        when (binding.rgStatusBahan.checkedRadioButtonId) {
            R.id.rb_status_bahan_1 -> value = "Diantar ke lokasi penjahit"
            R.id.rb_status_bahan_2 -> value = "Diambil oleh penjahit"
        }

        val asalBahan = "Pelanggan"
        val statusBahan = value
        val tanggalSelesai = binding.tvTanggalSelesai.text.toString().trim()
        val catatanPesanan = binding.etCatatanPesanan.text.toString().trim()
        val bahanJahit = binding.etBahanJahit.text.toString().trim()
        val panjangBahan = binding.etPanjangBahan.text.toString().trim()
        val lebarBahan = binding.etLebarBahan.text.toString().trim()
        val jumlahJahitanET = binding.etJumlahJahitan.text.toString().trim()
        val statusPesanan = "Belum diverifikasi"

        val ongkosPenjahit = data.ongkos_penjahit
        val jumlahJahitan : Int = jumlahJahitanET.toInt()

        val totalBiaya = jumlahJahitan * ongkosPenjahit!!

        val dataPesanan = Pesanan(
            0,
            idPelanggan,
            data.id_penjahit,
            data.id_detail_kategori,
            "",
            tanggalSelesai,
            "",
            catatanPesanan,
            "",
            bahanJahit,
            asalBahan,
            panjangBahan,
            lebarBahan,
            statusBahan,
            0,
            ongkosPenjahit,
            jumlahJahitan,
            0,
            totalBiaya,
            statusPesanan,
        )

        Log.d("Data Pesanan : ", dataPesanan.toString())
        viewModel.insertDataPesanan(dataPesanan)

    }


    companion object {
        @JvmStatic
        fun newInstance() =
            BahanPelangganFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}