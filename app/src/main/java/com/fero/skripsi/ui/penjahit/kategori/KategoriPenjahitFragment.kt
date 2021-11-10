package com.fero.skripsi.ui.penjahit.kategori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fero.skripsi.core.BaseFragment
import com.fero.skripsi.databinding.FragmentKategoriPenjahitBinding
import com.fero.skripsi.model.DetailKategori
import com.fero.skripsi.model.Penjahit
import com.google.gson.Gson

class KategoriPenjahitFragment : BaseFragment<FragmentKategoriPenjahitBinding>() {

    private lateinit var viewModel: ListKategoriViewModel
    private val dataPenjahit by lazy {
        baseGetInstance<Penjahit>("EXTRA_PENJAHIT_KATEGORI")
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentKategoriPenjahitBinding {
       return FragmentKategoriPenjahitBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
        viewModel = obtainViewModel<ListKategoriViewModel>().apply {

            listDetailKategori.observe(viewLifecycleOwner, {
                setupRvDetailKategori(it)
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

        viewModel.getListDetailKategori(dataPenjahit)
    }

    override fun setupUI(view: View, savedInstanceState: Bundle?) {

        binding.tvDetailKategori.text = "Kategori Penjahit " + dataPenjahit.nama_penjahit

        binding.btnAddCategory.setOnClickListener {
            val tambahDataKategoriFragment = TambahDataKategoriFragment()
            val fragmentManager = childFragmentManager
            tambahDataKategoriFragment.show(fragmentManager, TambahDataKategoriFragment::class.java.simpleName)
        }

    }

    private fun setupRvDetailKategori(data: List<DetailKategori>?) {
        val listKategoriAdapter = ListKategoriAdapter()
        listKategoriAdapter.setDetailKategori(data!!)

        binding.rvDetailKategori.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listKategoriAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            KategoriPenjahitFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}