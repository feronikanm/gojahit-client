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

    private lateinit var binding: ActivityUkuranDetailKategoriBinding
    companion object {
        const val EXTRA_DATA_KATEGORI = "EXTRA_DATA_KATEGORI"
        private const val ADD_UKURAN_TAG = "AddUkuran"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUkuranDetailKategoriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extraData: DetailKategoriPenjahit? = intent.extras?.getParcelable(EXTRA_DATA_KATEGORI)

        supportActionBar?.title = extraData?.nama_kategori

        binding.apply {
            tvNamaKategori.text = extraData!!.nama_kategori
            tvKetKategori.text = extraData.keterangan_kategori
            tvBahanJahit.text = extraData.bahan_jahit
            tvHargaBahan.text = extraData.harga_bahan
            tvOngkosPenjahit.text = extraData.ongkos_penjahit
            tvLamaWaktu.text = extraData.perkiraan_lama_waktu_pengerjaan

            Glide.with(this@UkuranDetailKategoriActivity)
                .load("${Constant.IMAGE_KATEGORI}${extraData.gambar_kategori}")
                .into(imgKategori)
        }

        val dataUkuran = UkuranDetailKategori(
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

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[UkuranViewModel::class.java]

        viewModel.apply {
            listUkuran.observe(this@UkuranDetailKategoriActivity, {
                setupRvUkuranDetailKategori(it)
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
        }

        viewModel.getUkuranByDetailKategori(dataUkuran)

        binding.btnAddUkuran.setOnClickListener {

            val tambahUkuranFragment = TambahUkuranFragment()

            //send data using bundle argument from activity to fragment
            val bundle = Bundle()
            val bundleData = Gson().toJson(extraData)
            bundle.putString("EXTRA_DETAIL_KATEGORI", bundleData)
            tambahUkuranFragment.arguments = bundle

            //show dialog fragment from activity
            tambahUkuranFragment.show(supportFragmentManager, ADD_UKURAN_TAG)
        }
    }

    private fun setupRvUkuranDetailKategori(data: List<UkuranDetailKategori>?) {
        val ukuranAdapter = UkuranDetailKategoriAdapter()
        ukuranAdapter.setUkuranDetailKategori(data!!)

        binding.rvUkuran.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ukuranAdapter
        }

        ukuranAdapter.setOnDeleteClickCallback(object : UkuranDetailKategoriAdapter.OnDeleteClickCallback{
            override fun onDeleteClicked(data: UkuranDetailKategori) {
                popupDelete(data)
            }
        })
    }

    private fun popupDelete(data: UkuranDetailKategori) {
        val materialAlertDialogBuilder = MaterialAlertDialogBuilder(this)
        materialAlertDialogBuilder.setTitle("Hapus Data")
            .setMessage("Apa anda yakin ingin menghapus data ini?")
            .setNegativeButton("Tidak", null)
            .setPositiveButton(
                "Hapus"
            ) { dialogInterface, i ->
                // panggil disini
                deleteDataUkuran(data)
            }
            .show()
    }

    private fun deleteDataUkuran(data: UkuranDetailKategori) {

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[UkuranViewModel::class.java]

        viewModel.apply {
            dataUkuran.observe(this@UkuranDetailKategoriActivity, {
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
        }
        viewModel.deleteDataUkuranDetailKategori(data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_edit -> {

                val editDataKategoriFragment = EditDataKategoriFragment()
                val extraData: DetailKategoriPenjahit? = intent.extras?.getParcelable(EXTRA_DATA_KATEGORI)

                val bundle = Bundle()
                val bundleData = Gson().toJson(extraData)
                bundle.putString("EXTRA_DETAIL_KATEGORI", bundleData)
                editDataKategoriFragment.arguments = bundle

                editDataKategoriFragment.show(
                    supportFragmentManager,
                    EditDataKategoriFragment::class.java.simpleName
                ) //cara memunculkan dialog box(2)
                return true
            }
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }

}