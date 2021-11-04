package com.fero.skripsi.ui.pelanggan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fero.skripsi.R
import com.fero.skripsi.core.BaseFragment
import com.fero.skripsi.databinding.FragmentDashboardPelangganBinding
import com.fero.skripsi.model.Kategori
import com.fero.skripsi.model.Nilai
import com.fero.skripsi.model.Pelanggan
import com.google.gson.Gson


class DashboardPelangganFragment : BaseFragment<FragmentDashboardPelangganBinding>() {

    private lateinit var viewModel: DashboardPelangganViewModel
    private var imageList = intArrayOf(R.drawable.img_slider1, R.drawable.img_slider2)

//    private val extraArgument: Pelanggan by lazy {
//        Gson().fromJson(
//            arguments?.getString("BUNDLE_REGISTER"), Pelanggan::class.java
//        )
//    }

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
                setupRvPenjahit(it)
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


    override fun setupUI(savedInstanceState: Bundle?) {
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

    private fun setupRvPenjahit(data: List<Nilai>) {
        val rekomendasiPenjahitAdapter = RekomendasiPenjahitAdapter()
        rekomendasiPenjahitAdapter.setPenjahit(data)

        binding.rvPenjahit.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rekomendasiPenjahitAdapter
        }
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