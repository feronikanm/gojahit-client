package com.fero.skripsi.ui.pelanggan.pesanan

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.DatePicker
import com.fero.skripsi.R
import com.fero.skripsi.databinding.ActivityPesananBinding
import com.fero.skripsi.model.DetailKategoriNilai
import com.fero.skripsi.model.Pelanggan
import com.fero.skripsi.ui.pelanggan.auth.EditDataPelangganActivity
import com.fero.skripsi.utils.PrefHelper
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class PesananActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPesananBinding

    companion object {
        const val EXTRA_DATA_DETAIL_KATEGORI_NILAI = "EXTRA_DATA_DETAIL_KATEGORI_NILAI"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Data Pesanan"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val data: DetailKategoriNilai? =
            intent.extras?.getParcelable(EXTRA_DATA_DETAIL_KATEGORI_NILAI)
        Log.d("Data hasil intent : ", data.toString())


        binding.btnBahanPelanggan.setOnClickListener {

            val fragmentManager = supportFragmentManager
            val bahanPelangganFragment = BahanPelangganFragment()

            val bundle = Bundle()
            val bundleData = Gson().toJson(data)
            bundle.putString("EXTRA_DATA_BAHAN_PELANGGAN", bundleData)
            bahanPelangganFragment.arguments = bundle

//            bahanPelangganFragment.show(supportFragmentManager, SHOW_PELANGGAN)

            val fragment =
                fragmentManager.findFragmentByTag(BahanPelangganFragment::class.java.simpleName)

            if (fragment !is BahanPelangganFragment) {
                fragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        bahanPelangganFragment,
                        BahanPelangganFragment::class.java.simpleName
                    )
                    .commit()
            }
        }

        binding.btnBahanPenjahit.setOnClickListener {

            val fragmentManager = supportFragmentManager
            val bahanPenjahitFragment = BahanPenjahitFragment()
            val bundle = Bundle()
            val bundleData = Gson().toJson(data)
            bundle.putString("EXTRA_DATA_BAHAN_PENJAHIT", bundleData)
            bahanPenjahitFragment.arguments = bundle

//            bahanPenjahitFragment.show(supportFragmentManager, SHOW_PENJAHIT)

            val fragment =
                fragmentManager.findFragmentByTag(BahanPenjahitFragment::class.java.simpleName)

            if (fragment !is BahanPenjahitFragment) {
                fragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        bahanPenjahitFragment,
                        BahanPenjahitFragment::class.java.simpleName
                    )
                    .commit()


            }

//        val fragmentManager = supportFragmentManager
//        val pilihBahanPesananActivity = PilihBahanPesananFragment()
//        val fragment = fragmentManager.findFragmentByTag(PilihBahanPesananFragment::class.java.simpleName)
//
//        if(fragment !is PilihBahanPesananFragment){
//            fragmentManager
//                .beginTransaction()
//                .add(R.id.fragment_container, pilihBahanPesananActivity, PilihBahanPesananFragment::class.java.simpleName)
//                .commit()
        }

        //langsung
//        val sdf = SimpleDateFormat("yyyy-M-dd")

        //ribet
//        var cal2 = Calendar.getInstance()
//        val year = cal2.get(Calendar.YEAR)
//        val month = cal2.get(Calendar.MONTH)
//        val day = cal2.get(Calendar.DAY_OF_MONTH)
//        val sdf = SimpleDateFormat("" + year + "-" + month + "-" + day)
//        val currentDate = sdf.format(Date())
//        binding.tvTanggalSekarang.text = "Tanggal hari ini : " + currentDate
//
//        binding.tvBeda.text = "Beda : " + differentDays()


    }

//    private fun differentDays(): Long {
//        val diff = cal.timeInMillis - cal2.timeInMillis
//        val diffDays = diff / (24 * 60 * 60 * 1000)
//        return diffDays
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}