package com.fero.skripsi.ui.penjahit.ukuran

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fero.skripsi.databinding.FragmentTambahUkuranBinding
import com.fero.skripsi.model.UkuranDetailKategori
import com.fero.skripsi.ui.penjahit.ukuran.adapter.TambahUkuranAdapter
import com.fero.skripsi.ui.penjahit.ukuran.viewmodel.UkuranViewModel
import com.fero.skripsi.utils.ViewModelFactory
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_penjahit.*

class TambahUkuranFragment : DialogFragment() {

    private lateinit var binding: FragmentTambahUkuranBinding
    val EXTRA_DETAIL_KATEGORI = "EXTRA_DETAIL_KATEGORI"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTambahUkuranBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val parentActivity: UkuranDetailKategoriActivity by lazy {
        (activity as UkuranDetailKategoriActivity)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get argument from activty UkuranDetailKategoriActivity
        val bundleData = arguments?.getString(EXTRA_DETAIL_KATEGORI)
        val data = Gson().fromJson(bundleData, UkuranDetailKategori::class.java)

        binding.tvNamaKategori.text = data.nama_kategori

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[UkuranViewModel::class.java]

        viewModel.apply {
            listUkuran.observe(this@TambahUkuranFragment, {
                setupRvUkuran(it)
            })

            vmDataUkuran.observe(this@TambahUkuranFragment, {
                dialog?.dismiss()
                parentActivity.refreshGetDataViewModel()
            })

            messageSuccess.observe(this@TambahUkuranFragment, {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })

            messageFailed.observe(this@TambahUkuranFragment, {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })

            getDataUkuran()
        }

        binding.btnCancel.setOnClickListener {
            dialog?.cancel()
        }

    }

    private fun setupRvUkuran(data: List<UkuranDetailKategori>?) {
        val tambahUkuranAdapter = TambahUkuranAdapter()
        tambahUkuranAdapter.setUkuran(data!!)

        binding.rvUkuran.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tambahUkuranAdapter
        }

        tambahUkuranAdapter.setItemClickCallback(object : TambahUkuranAdapter.OnItemClickCallback{
            override fun onItemClicked(data: UkuranDetailKategori) {
                selectedUkuran(data)
            }

        })
    }

    private fun selectedUkuran(data: UkuranDetailKategori) {

        //get argument from activty UkuranDetailKategoriActivity
        val bundleData = arguments?.getString(EXTRA_DETAIL_KATEGORI)
        val detailKategori = Gson().fromJson(bundleData, UkuranDetailKategori::class.java)

        //inisialized view model
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[UkuranViewModel::class.java]

        val dataUkuranDetailKategori = UkuranDetailKategori(
            0,
            detailKategori.id_detail_kategori,
            0,
            0,
            data.id_ukuran,
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

        viewModel.insertDataUkuranDetailKategori(dataUkuranDetailKategori)

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TambahUkuranFragment().apply {
                arguments = Bundle().apply {}
            }
    }

}