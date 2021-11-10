package com.fero.skripsi.ui.pelanggan.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fero.skripsi.R
import com.fero.skripsi.core.BaseFragment
import com.fero.skripsi.databinding.FragmentDashboardPelangganBinding
import com.fero.skripsi.model.Kategori
import com.fero.skripsi.model.Nilai
import com.fero.skripsi.model.Pelanggan


class DashboardPelangganFragment : BaseFragment<FragmentDashboardPelangganBinding>() {

    private lateinit var viewModel: DashboardPelangganViewModel
    private var imageList = intArrayOf(R.drawable.img_slider1, R.drawable.img_slider2)
    private val dataPelanggan by lazy {
        baseGetInstance<Pelanggan>("EXTRA_PELANGGAN_DASHBOARD")
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDashboardPelangganBinding {
        return FragmentDashboardPelangganBinding.inflate(inflater, container, false)
    }


    override fun setupViewModel() {
        viewModel = obtainViewModel<DashboardPelangganViewModel>().apply {

            listKategori.observe(viewLifecycleOwner, {
                setupRvKategori(it)
                binding.tvKategori.visibility = View.VISIBLE
            })

            listNilai.observe(viewLifecycleOwner, {
                setupRvPenjahit(it, dataPelanggan)
                binding.tvRekomendasi.visibility = View.VISIBLE
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

        binding.tvKategori.visibility = View.INVISIBLE
        viewModel.getDataKategori()

        binding.tvRekomendasi.visibility = View.INVISIBLE
        viewModel.getDataPenjahit()
    }

    override fun setupUI(view: View, savedInstanceState: Bundle?) {

        Log.d("Data Pelanggan : ", dataPelanggan.toString())

        binding.apply {
            viewFlipper.setInAnimation(context, android.R.anim.slide_in_left)
            viewFlipper.setOutAnimation(context, android.R.anim.slide_out_right)
            for (image in imageList) {
                val imageView = ImageView(context)
                imageView.setImageResource(image)
                viewFlipper.addView(imageView)
                viewFlipper.flipInterval = 5000
                viewFlipper.isAutoStart = true
            }

            tvNamaPelanggan.text = dataPelanggan.nama_pelanggan
        }

//        binding.tvText.text = extraArgument.nama_pelanggan

//        binding.btnDetail.setOnClickListener {
//            val moveIntent = Intent(context, DetailPenjahitPelangganActivity::class.java)
//            startActivity(moveIntent)
//        }
    }

    private fun setupRvKategori(data: List<Kategori>?) {
        val kategoriPenjahitAdapter = KategoriPenjahitAdapter()
        data?.let { kategoriPenjahitAdapter.setKategori(it) }

        binding.rvKategori.apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = kategoriPenjahitAdapter
        }
    }

    private fun setupRvPenjahit(data: List<Nilai>, dataPelanggan: Pelanggan) {
        val rekomendasiPenjahitAdapter = RekomendasiPenjahitAdapter()
        rekomendasiPenjahitAdapter.setupDataPelanggan(dataPelanggan)
        rekomendasiPenjahitAdapter.setPenjahit(data)

        binding.rvPenjahit.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rekomendasiPenjahitAdapter
        }

        rekomendasiPenjahitAdapter.setOnItemClickCallback(object :
            RekomendasiPenjahitAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Nilai) {
                selectedPenjahit(data)
            }

        })


    }

    private fun selectedPenjahit(data: Nilai) {
        Toast.makeText(context, "Kamu memilih " + data.nama_penjahit, Toast.LENGTH_SHORT).show()
        Log.d("Test", "CLICK FROM ADAPTER")

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DashboardPelangganFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}