package com.fero.skripsi.ui.detailkategoriInGridViewKategori

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fero.skripsi.core.BaseFragment
import com.fero.skripsi.databinding.FragmentDetailKategoriBinding
import com.fero.skripsi.model.DetailKategoriNilai
import com.fero.skripsi.model.Pelanggan
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.ui.detailkategoriInGridViewKategori.adapter.DetailKetegoriAdapter
import com.fero.skripsi.ui.pelanggan.dashboard.viewmodel.DashboardPelangganViewModel
import com.fero.skripsi.ui.pelanggan.detail.DetailPenjahitPelangganActivity
import com.fero.skripsi.ui.penjahit.dashboard.DetailPenjahitActivity


class DetailKategoriFragment : BaseFragment<FragmentDetailKategoriBinding>() {

    private lateinit var dashboardPelangganViewModel: DashboardPelangganViewModel
    private val data by lazy {
        baseGetInstance<DetailKategoriNilai>("EXTRA_PENJAHIT_BY_KATEGORI")
    }

    private val extraDataPelanggan by lazy {
        baseGetInstance<Pelanggan>("EXTRA_PENJAHIT_BY_KATEGORI_EXTRA_PELANGGAN")
    }

    private val extraDataPenjahit by lazy {
        baseGetInstance<Penjahit>("EXTRA_PENJAHIT_BY_KATEGORI_EXTRA_PENJAHIT")
    }

    private val statusAccount by lazy {
        baseGetInstance<String>("EXTRA_ACCOUNT_STATUS")
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailKategoriBinding {
        return FragmentDetailKategoriBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
        dashboardPelangganViewModel = obtainViewModel<DashboardPelangganViewModel>().apply {

            listDataPenjahitByKategori.observe(viewLifecycleOwner, {

                if (statusAccount == "status_penjahit") {
                    setupRvPenjahitByKategoriInPenjahit(it, extraDataPenjahit)

                } else if (statusAccount == "status_pelanggan") {
                    setupRvPenjahitByKategoriInPelanggan(it, extraDataPelanggan)

                }
            })

            eventShowProgress.observe(viewLifecycleOwner, {
                setupEventProgressView(binding.progressBar, it)
            })

            eventErrorMessage.observe(viewLifecycleOwner, {
                showToast(it)
            })

            eventIsEmpty.observe(viewLifecycleOwner, {
                // setupEventEmptyView(binding?.{EMPTY_VIEW MU}!! ,it)
            })

        }
        dashboardPelangganViewModel.getDataPenjahitByKategori(data)
    }

    private fun setupRvPenjahitByKategoriInPenjahit(
        data: List<DetailKategoriNilai>?,
        dataPenjahit: Penjahit
    ) {
        val detailKetegoriAdapter = DetailKetegoriAdapter()
        detailKetegoriAdapter.setupDataPenjahit(dataPenjahit)
        detailKetegoriAdapter.setPenjahitByKategori(data!!)

        binding.rvPenjahit.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = detailKetegoriAdapter
        }

        detailKetegoriAdapter.setOnItemClickCallback(object :
            DetailKetegoriAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DetailKategoriNilai) {

                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
                Toast.makeText(context, "Kamu memilih " + data.nama_penjahit, Toast.LENGTH_SHORT).show()
                Log.d("Test", "CLICK FROM ADAPTER")
                val intent = Intent(binding.root.context, DetailPenjahitActivity::class.java)
                intent.putExtra(DetailPenjahitActivity.EXTRA_DATA_NILAI, data)
                intent.putExtra(DetailPenjahitActivity.EXTRA_DATA_PENJAHIT, dataPenjahit)
                startActivity(intent)

            }

        })
    }

    private fun setupRvPenjahitByKategoriInPelanggan(
        data: List<DetailKategoriNilai>?,
        dataPelanggan: Pelanggan
    ) {
        val detailKetegoriAdapter = DetailKetegoriAdapter()
        detailKetegoriAdapter.setupDataPelanggan(dataPelanggan)
        detailKetegoriAdapter.setPenjahitByKategori(data!!)

        binding.rvPenjahit.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = detailKetegoriAdapter
        }

        detailKetegoriAdapter.setOnItemClickCallback(object :
            DetailKetegoriAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DetailKategoriNilai) {

                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
                Toast.makeText(context, "Kamu memilih " + data.nama_penjahit, Toast.LENGTH_SHORT).show()
                Log.d("Test", "CLICK FROM ADAPTER")

                val intent = Intent(binding.root.context, DetailPenjahitPelangganActivity::class.java)
                intent.putExtra(DetailPenjahitPelangganActivity.EXTRA_DATA_PENJAHIT, data)
                intent.putExtra(DetailPenjahitPelangganActivity.EXTRA_DATA_PELANGGAN, dataPelanggan)
                startActivity(intent)

            }

        })
    }

//    private fun setupRvPenjahitByKategori(data: List<DetailKategoriNilai>?, dataPelanggan: Pelanggan) {
//        val detailKetegoriAdapter = DetailKetegoriAdapter()
//        detailKetegoriAdapter.setupDataPelanggan(dataPelanggan)
//        detailKetegoriAdapter.setPenjahitByKategori(data!!)
//
//        binding.rvPenjahit.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = detailKetegoriAdapter
//        }
//
//        detailKetegoriAdapter.setOnItemClickCallback(object :
//            DetailKetegoriAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: DetailKategoriNilai) {
//
//
//                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
//                Toast.makeText(context, "Kamu memilih " + data.nama_penjahit, Toast.LENGTH_SHORT)
//                    .show()
//                Log.d("Test", "CLICK FROM ADAPTER")
//
//                if (statusAccount == "status_penjahit") {
//                    val intent = Intent(binding.root.context, DetailPenjahitActivity::class.java)
//                    intent.putExtra(DetailPenjahitActivity.EXTRA_PENJAHIT, data)
//                    startActivity(intent)
//                } else if (statusAccount == "status_pelanggan") {
//                    val intent =
//                        Intent(binding.root.context, DetailPenjahitPelangganActivity::class.java)
//                    intent.putExtra(DetailPenjahitPelangganActivity.EXTRA_DATA_PENJAHIT, data)
//                    intent.putExtra(DetailPenjahitPelangganActivity.EXTRA_DATA_PELANGGAN, dataPelanggan)
//                    startActivity(intent)
//                }
//            }
//
//        })
//    }

    override fun setupUI(view: View, savedInstanceState: Bundle?) {

        binding.tvPenjahitByKategori.text =
            "Data Penjahit yang memiliki kategori " + data.nama_kategori
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DetailKategoriFragment().apply {
                arguments = Bundle().apply {}
            }

    }

//    private lateinit var binding: FragmentDetailKategoriBinding
//    val EXTRA_PENJAHIT_BY_KATEGORI = "EXTRA_PENJAHIT_BY_KATEGORI"

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentDetailKategoriBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val bundleData = arguments?.getString(EXTRA_PENJAHIT_BY_KATEGORI)
//        val data = Gson().fromJson(bundleData, DetailKategoriNilai::class.java)
//    }


}