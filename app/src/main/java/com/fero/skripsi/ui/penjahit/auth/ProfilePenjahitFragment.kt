package com.fero.skripsi.ui.penjahit.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.fero.skripsi.databinding.FragmentProfilePenjahitBinding
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.ui.main.PilihUserActivity
import com.fero.skripsi.ui.pelanggan.auth.viewmodel.AuthPelangganViewModel
import com.fero.skripsi.ui.penjahit.auth.EditDataPenjahitActivity.Companion.EXTRA_DATA_PENJAHIT
import com.fero.skripsi.ui.penjahit.auth.viewmodel.AuthPenjahitViewModel
import com.fero.skripsi.utils.Constant
import com.fero.skripsi.utils.PrefHelper
import com.fero.skripsi.utils.ViewModelFactory
import com.google.gson.Gson
import java.text.DecimalFormat

class ProfilePenjahitFragment : Fragment() {

    private lateinit var binding: FragmentProfilePenjahitBinding
    lateinit var prefHelper: PrefHelper

    val EXTRA_PENJAHIT = "EXTRA_PENJAHIT"

    private val factory by lazy {
        ViewModelFactory.getInstance(requireContext())
    }

    private val authPenjahitViewModel by lazy {
        ViewModelProvider(this, factory)[AuthPenjahitViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        authPenjahitViewModel.apply {
            dataPenjahitVM.observe(viewLifecycleOwner, {
                binding.apply {

                    tvNamaToko.text = it.nama_toko
                    tvNamaToko2.text = it.nama_toko
                    tvNamaPenjahit.text = it.nama_penjahit
                    tvNamaPenjahit2.text = it.nama_penjahit
                    tvEmailPenjahit.text = it.email_penjahit
                    tvTelepon.text = it.telp_penjahit
                    tvAlamatPenjahit.text = it.alamat_penjahit
                    tvKetToko.text = it.keterangan_toko
                    tvSpesifikasi.text = it.spesifikasi_penjahit
                    tvJangkauan.text = it.jangkauan_kategori_penjahit
                    tvLatitude.text = it.latitude_penjahit
                    tvLongitude.text = it.longitude_penjahit
                    tvHariBuka.text = it.hari_buka
                    tvJamBuka.text = it.jam_buka
                    tvJamTutup.text = it.jam_tutup

                    Glide.with(requireContext())
                        .load("${Constant.IMAGE_PENJAHIT}${it.foto_penjahit}")
                        .into(imgPenjahit)


                }
            })
        }
        authPenjahitViewModel.getDataPenjahitById(dataPenjahit)

//        binding.apply {
//            tvNamaToko.text = dataPenjahit.nama_toko
//            tvNamaToko2.text = dataPenjahit.nama_toko
//            tvNamaPenjahit.text = dataPenjahit.nama_penjahit
//            tvNamaPenjahit2.text = dataPenjahit.nama_penjahit
//            tvEmailPenjahit.text = dataPenjahit.email_penjahit
//            tvTelepon.text = dataPenjahit.telp_penjahit
//            tvAlamatPenjahit.text = dataPenjahit.alamat_penjahit
//            tvKetToko.text = dataPenjahit.keterangan_toko
//            tvSpesifikasi.text = dataPenjahit.spesifikasi_penjahit
//            tvJangkauan.text = dataPenjahit.jangkauan_kategori_penjahit
//            tvLatitude.text = dataPenjahit.latitude_penjahit
//            tvLongitude.text = dataPenjahit.longitude_penjahit
//            tvHariBuka.text = dataPenjahit.hari_buka
//            tvJamBuka.text = dataPenjahit.jam_buka
//            tvJamTutup.text = dataPenjahit.jam_tutup
//
//            context?.let {
//                Glide.with(it)
//                    .load("${Constant.IMAGE_PENJAHIT}${dataPenjahit.foto_penjahit}")
//                    .into(imgPenjahit)
//            }
//
////            if(dataPenjahit!!.nilai_akhir != null){
////                val df = DecimalFormat("#.#")
////                val extraRating = extraDataNilai!!.nilai_akhir
////                val rating = df.format(extraRating)
////                contentBinding.tvRating.text = rating.toString()
////            }
////            else{
////                contentBinding.tvRating.text = "Belum ada penilaian"
////            }
//        }

        binding.btnEditProfil.setOnClickListener {
            val moveIntent = Intent(context, EditDataPenjahitActivity::class.java)
            moveIntent.putExtra(EXTRA_DATA_PENJAHIT, dataPenjahit)
            startActivity(moveIntent)
        }

        binding.btnLogout.setOnClickListener {
            prefHelper.clear()
            Toast.makeText(context, "Keluar", Toast.LENGTH_SHORT).show()
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