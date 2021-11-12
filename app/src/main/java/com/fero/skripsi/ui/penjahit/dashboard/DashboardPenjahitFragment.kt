package com.fero.skripsi.ui.penjahit.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fero.skripsi.R
import com.fero.skripsi.core.BaseFragment
import com.fero.skripsi.databinding.FragmentDashboardPenjahitBinding
import com.fero.skripsi.model.DetailKategoriNilai
import com.fero.skripsi.model.Kategori
import com.fero.skripsi.model.Nilai
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.ui.pelanggan.dashboard.DetailKategoriFragment
import com.fero.skripsi.ui.pelanggan.dashboard.KategoriPenjahitAdapter
import com.google.gson.Gson

class DashboardPenjahitFragment : BaseFragment<FragmentDashboardPenjahitBinding>() {

    private lateinit var viewModel: DashboardPenjahitViewModel
    private var imageList = intArrayOf(R.drawable.img_slider1, R.drawable.img_slider2)
    private val dataPenjahit by lazy {
        baseGetInstance<Penjahit>("EXTRA_PENJAHIT_DASHBOARD")
    }

    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentDashboardPenjahitBinding {
        return FragmentDashboardPenjahitBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
        viewModel = obtainViewModel<DashboardPenjahitViewModel>().apply {

            listKategori.observe(viewLifecycleOwner, {
                setupRvKategori(it)
                binding.tvKategori.visibility = View.VISIBLE
            })

            listNilai.observe(viewLifecycleOwner, {
                //dataPenjahit adalah extraData Penjahit dari login Penjahit
                setupRvPenjahit(it, dataPenjahit)
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
    }

    private fun setupRvKategori(data: List<DetailKategoriNilai>?) {
        val kategoriAdapter = KategoriAdapter()
        data?.let { kategoriAdapter.setKategori(it) }

        binding.rvKategori.apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = kategoriAdapter
        }

        kategoriAdapter.setOnItemClickCallback(object : KategoriAdapter.OnItemClickCallback{
            override fun onItemClicked(data: DetailKategoriNilai) {

                val detailKategoriFragment = DetailKategoriFragment()

                val bundle = Bundle()
                val bundleData = Gson().toJson(data)
                bundle.putString("EXTRA_PENJAHIT_BY_KATEGORI", bundleData)
                detailKategoriFragment.arguments = bundle

                val fragmentManager = parentFragmentManager
                fragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, detailKategoriFragment, DetailKategoriFragment::class.java.simpleName)
                    addToBackStack(null)
                    commit()
                }
            }
        })

    }

    private fun setupRvPenjahit(data: List<DetailKategoriNilai>, dataPenjahit: Penjahit) {
        val penjahitAdapter = PenjahitAdapter()
        penjahitAdapter.setupDataPenjahit(dataPenjahit)
        penjahitAdapter.setPenjahit(data)

        binding.rvPenjahit.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = penjahitAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DashboardPenjahitFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}