package com.fero.skripsi.ui.penjahit

import android.content.Intent
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
import com.fero.skripsi.model.Kategori
import com.fero.skripsi.model.Nilai
import com.fero.skripsi.ui.main.PilihUserActivity


class DashboardPenjahitFragment : BaseFragment<FragmentDashboardPenjahitBinding>() {

    private lateinit var viewModel: DashboardPenjahitViewModel
    private var imageList = intArrayOf(R.drawable.img_slider1, R.drawable.img_slider2)

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDashboardPenjahitBinding {
        return FragmentDashboardPenjahitBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
        viewModel = obtainViewModel<DashboardPenjahitViewModel>().apply {

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

//            btnHello.setOnClickListener {
//                Toast.makeText(context, "Di Klik" , Toast.LENGTH_SHORT).show()
//                val moveIntent = Intent(context, PilihUserActivity::class.java)
//                startActivity(moveIntent)
//            }

//            tvKategori.movementMethod = LinkMovementMethod.getInstance()
        }
    }

    private fun setupRvKategori(data: List<Kategori>?) {
        val kategoriAdapter = KategoriAdapter()
        data?.let { kategoriAdapter.setKategori(it) }

        binding.rvKategori.apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = kategoriAdapter
        }
    }

    private fun setupRvPenjahit(data: List<Nilai>) {
        val penjahitAdapter = PenjahitAdapter()
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