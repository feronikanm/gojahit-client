package com.fero.skripsi.ui.penjahit

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.fero.skripsi.databinding.FragmentProfilePenjahitBinding
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.ui.main.PilihUserActivity
import com.fero.skripsi.ui.penjahit.EditDataPenjahitActivity.Companion.EXTRA_DATA_PENJAHIT
import com.fero.skripsi.utils.Constant
import com.fero.skripsi.utils.PrefHelper
import com.google.gson.Gson

class ProfilePenjahitFragment : Fragment() {

    private lateinit var binding: FragmentProfilePenjahitBinding
    lateinit var prefHelper: PrefHelper

    val EXTRA_PENJAHIT = "EXTRA_PENJAHIT"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfilePenjahitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        supportActionBar?.title = "Data Penjahit"
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        prefHelper = PrefHelper(context)

        val bundleData = arguments?.getString(EXTRA_PENJAHIT)
        val dataPenjahit = Gson().fromJson(bundleData, Penjahit::class.java)

        binding.apply {
            tvNamaToko.text = dataPenjahit.nama_toko
            tvNamaToko2.text = dataPenjahit.nama_toko
            tvNamaPenjahit.text = dataPenjahit.nama_penjahit
            tvNamaPenjahit2.text = dataPenjahit.nama_penjahit
            tvEmailPenjahit.text = dataPenjahit.email_penjahit
            tvTelepon.text = dataPenjahit.telp_penjahit
            tvAlamatPenjahit.text = dataPenjahit.alamat_penjahit
            tvKetToko.text = dataPenjahit.keterangan_toko
            tvSpesifikasi.text = dataPenjahit.spesifikasi_penjahit
            tvJangkauan.text = dataPenjahit.jangkauan_kategori_penjahit
            tvLatitude.text = dataPenjahit.latitude_penjahit
            tvLongitude.text = dataPenjahit.longitude_penjahit
            tvHariBuka.text = dataPenjahit.hari_buka
            tvJamBuka.text = dataPenjahit.jam_buka
            tvJamTutup.text = dataPenjahit.jam_tutup

            context?.let {
                Glide.with(it)
                    .load("${Constant.IMAGE_PENJAHIT}${dataPenjahit.foto_penjahit}")
                    .into(imgPenjahit)
            }

        }


        binding.btnEditProfil.setOnClickListener {
            val moveIntent = Intent(context, EditDataPenjahitActivity::class.java)
            moveIntent.putExtra(EXTRA_DATA_PENJAHIT, dataPenjahit)
            startActivity(moveIntent)
        }

        binding.btnLogout.setOnClickListener {
            prefHelper.clear()
            Toast.makeText(context, "Keluar" , Toast.LENGTH_SHORT).show()
            val moveIntent = Intent(context, PilihUserActivity::class.java)
            startActivity(moveIntent)
            activity?.finish()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProfilePenjahitFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}