package com.fero.skripsi.ui.pelanggan

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.fero.skripsi.databinding.FragmentProfilePelangganBinding
import com.fero.skripsi.model.Pelanggan
import com.fero.skripsi.ui.main.PilihUserActivity
import com.fero.skripsi.utils.Constant
import com.fero.skripsi.utils.PrefHelper
import com.google.gson.Gson

class ProfilePelangganFragment : Fragment() {

    private lateinit var binding : FragmentProfilePelangganBinding
    lateinit var prefHelper: PrefHelper

    val EXTRA_PELANGGAN = "EXTRA_PELANGGAN"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfilePelangganBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefHelper = PrefHelper(context)

        val bundleData = arguments?.getString(EXTRA_PELANGGAN)
        val dataPelanggan = Gson().fromJson(bundleData, Pelanggan::class.java)

        binding.apply {
            tvNamaPelanggan.text = dataPelanggan.nama_pelanggan
            tvAlamatPelanggan.text = dataPelanggan.alamat_pelanggan
            tvEmailPelanggan.text = dataPelanggan.email_pelanggan
            tvEmailPelanggan2.text = dataPelanggan.email_pelanggan
            tvJkPelanggan.text = dataPelanggan.jk_pelanggan
            tvTelepon.text = dataPelanggan.telp_pelanggan
            tvLatPelanggan.text = dataPelanggan.latitude_pelanggan.toString()
            tvLongPelanggan.text = dataPelanggan.longitude_pelanggan.toString()

            context?.let {
                Glide.with(it)
                    .load("${Constant.IMAGE_PELANGGAN}${dataPelanggan.foto_pelanggan}")
                    .into(imgPelanggan)
            }
        }

        binding.btnEditProfil.setOnClickListener {
            Toast.makeText(context, "Edit Data " + dataPelanggan.nama_pelanggan, Toast.LENGTH_SHORT).show()
            val moveIntent = Intent(context, EditDataPelangganActivity::class.java)
            moveIntent.putExtra(EditDataPelangganActivity.EXTRA_DATA_PELANGGAN, dataPelanggan)
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
            ProfilePelangganFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}