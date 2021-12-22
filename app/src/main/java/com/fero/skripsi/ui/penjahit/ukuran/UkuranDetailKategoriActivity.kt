package com.fero.skripsi.ui.penjahit.ukuran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.fero.skripsi.R
import com.fero.skripsi.databinding.ActivityUkuranDetailKategoriBinding
import com.fero.skripsi.model.DetailKategoriPenjahit
import com.fero.skripsi.model.UkuranDetailKategori
import com.fero.skripsi.ui.penjahit.kategori.EditDataKategoriFragment
import com.fero.skripsi.ui.penjahit.ukuran.adapter.UkuranDetailKategoriAdapter
import com.fero.skripsi.ui.penjahit.ukuran.viewmodel.UkuranViewModel
import com.fero.skripsi.utils.Constant
import com.fero.skripsi.utils.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson

class UkuranDetailKategoriActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA_KATEGORI = "EXTRA_DATA_KATEGORI"
        private const val ADD_UKURAN_TAG = "AddUkuran"
    }

    private val binding: ActivityUkuranDetailKategoriBinding by lazy {
        ActivityUkuranDetailKategoriBinding.inflate(layoutInflater)
    }

    private val ukuranAdapter by lazy {
        UkuranDetailKategoriAdapter()
    }

    private val factory by lazy {
        ViewModelFactory.getInstance(this)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, factory)[UkuranViewModel::class.java]
    }

    private val extraData: DetailKategoriPenjahit? by lazy {
        intent.getParcelableExtra(EXTRA_DATA_KATEGORI)
    }

    private val dataUkuran by lazy {
        UkuranDetailKategori(
            0,
            extraData?.id_detail_kategori,
            0,
            0,
            0,
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
        )
    }

    private fun setupViewModel() {
        viewModel.apply {

            listUkuran.observe(this@UkuranDetailKategoriActivity, {
                setupRV(it)
            })

            messageSuccess.observe(this@UkuranDetailKategoriActivity, {
                Toast.makeText(this@UkuranDetailKategoriActivity, it, Toast.LENGTH_SHORT).show()
            })

            eventShowProgress.observe(this@UkuranDetailKategoriActivity, {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            })

            messageFailed.observe(this@UkuranDetailKategoriActivity, {
                Toast.makeText(this@UkuranDetailKategoriActivity, it, Toast.LENGTH_SHORT).show()
            })

            vmDataUkuran.observe(this@UkuranDetailKategoriActivity, {

            })

            messageSuccess.observe(this@UkuranDetailKategoriActivity, {
                Toast.makeText(this@UkuranDetailKategoriActivity, it, Toast.LENGTH_SHORT).show()
            })

            eventShowProgress.observe(this@UkuranDetailKategoriActivity, {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            })

            onSuccessDeleteState.observe(this@UkuranDetailKategoriActivity, {
                if (it) {

                    deleteItemPosition.observe(this@UkuranDetailKategoriActivity, {
                        ukuranAdapter.deleteItem(it)
                    })

                }
            })

        }

        viewModel.getUkuranByDetailKategori(dataUkuran)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title = extraData?.nama_kategori
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupViewModel()

        binding.apply {
            tvNamaKategori.text = extraData!!.nama_kategori
            tvKetKategori.text = extraData?.keterangan_kategori
            tvBahanJahit.text = extraData?.bahan_jahit
            tvHargaBahan.text = extraData?.harga_bahan
            tvOngkosPenjahit.text = extraData?.ongkos_penjahit
            tvLamaWaktu.text = extraData?.perkiraan_lama_waktu_pengerjaan

            Glide.with(this@UkuranDetailKategoriActivity)
                .load("${Constant.IMAGE_KATEGORI}${extraData?.gambar_kategori}")
                .into(imgKategori)

            btnAddUkuran.setOnClickListener {

                val tambahUkuranFragment = TambahUkuranFragment()

                //send data using bundle argument from activity to fragment
                val bundle = Bundle()
                val bundleData = Gson().toJson(extraData)
                bundle.putString("EXTRA_DETAIL_KATEGORI", bundleData)
                tambahUkuranFragment.arguments = bundle

                //show dialog fragment from activity
                tambahUkuranFragment.show(supportFragmentManager, ADD_UKURAN_TAG)
            }

            rvUkuran.layoutManager = LinearLayoutManager(this@UkuranDetailKategoriActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setupRV(data: List<UkuranDetailKategori>?) {
        ukuranAdapter.setUkuranDetailKategori(data!!)
        ukuranAdapter.setOnDeleteClickCallback(object : UkuranDetailKategoriAdapter.OnDeleteClickCallback {
            override fun onDeleteClicked(data: UkuranDetailKategori, position: Int) {
                popupDelete(data, position)
            }
        })
        binding.rvUkuran.adapter = ukuranAdapter
    }

    private fun popupDelete(data: UkuranDetailKategori, position: Int) {
        val materialAlertDialogBuilder = MaterialAlertDialogBuilder(this)
        materialAlertDialogBuilder.setTitle("Hapus Data")
            .setMessage("Apa anda yakin ingin menghapus data ini?")
            .setNegativeButton("Tidak", null)
            .setPositiveButton(
                "Hapus"
            ) { dialogInterface, i ->
                // panggil disini
                deleteDataUkuran(data, position)
            }
            .show()
    }

    private fun deleteDataUkuran(data: UkuranDetailKategori, position: Int) {
        viewModel.deleteDataUkuranDetailKategori(data, position)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.option_menu, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.btn_edit -> {
//
//                val editDataKategoriFragment = EditDataKategoriFragment()
//
//                val bundle = Bundle()
//                val bundleData = Gson().toJson(extraData)
//                bundle.putString("EXTRA_DETAIL_KATEGORI", bundleData)
//                editDataKategoriFragment.arguments = bundle
//
//                editDataKategoriFragment.show(
//                    supportFragmentManager,
//                    EditDataKategoriFragment::class.java.simpleName
//                )
//                return true
//            }
//            else -> {}
//        }
//        return super.onOptionsItemSelected(item)
//    }

    fun refreshGetDataViewModel() {
        viewModel.getUkuranByDetailKategori(dataUkuran)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}