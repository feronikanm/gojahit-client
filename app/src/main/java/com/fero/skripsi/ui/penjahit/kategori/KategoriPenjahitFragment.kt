package com.fero.skripsi.ui.penjahit.kategori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fero.skripsi.core.BaseFragment
import com.fero.skripsi.databinding.FragmentKategoriPenjahitBinding
import com.fero.skripsi.model.ListDetailKategori
import com.fero.skripsi.model.Penjahit
import com.google.gson.Gson

class KategoriPenjahitFragment : BaseFragment<FragmentKategoriPenjahitBinding>() {

    private lateinit var penjahitViewModel: KategoriPenjahitViewModel
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
        penjahitViewModel = obtainViewModel<KategoriPenjahitViewModel>().apply {

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

        penjahitViewModel.getListDetailKategori(dataPenjahit)
    }

    override fun setupUI(view: View, savedInstanceState: Bundle?) {

        binding.tvDetailKategori.text = "Kategori Penjahit " + dataPenjahit.nama_penjahit

        binding.btnAddCategory.setOnClickListener {
            val tambahDataKategoriFragment = TambahDataKategoriFragment()

            val bundle = Bundle()
            val bundleData = Gson().toJson(dataPenjahit)
            bundle.putString("EXTRA_PENJAHIT", bundleData)
            tambahDataKategoriFragment.arguments = bundle

            val fragmentManager = childFragmentManager //cara memunculkan dialog box(1)
            tambahDataKategoriFragment.show(fragmentManager, TambahDataKategoriFragment::class.java.simpleName) //cara memunculkan dialog box(2)
        }

    }

    private fun setupRvDetailKategori(data: List<ListDetailKategori>?) {
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