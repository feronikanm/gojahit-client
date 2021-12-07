package com.fero.skripsi.ui.penjahit.kategori

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fero.skripsi.R
import com.fero.skripsi.core.BaseFragment
import com.fero.skripsi.databinding.FragmentKategoriPenjahitBinding
import com.fero.skripsi.model.DetailKategoriPenjahit
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.ui.penjahit.kategori.adapter.ListKategoriAdapter
import com.fero.skripsi.ui.penjahit.kategori.viewmodel.KategoriPenjahitViewModel
import com.fero.skripsi.ui.penjahit.ukuran.UkuranDetailKategoriActivity
import com.fero.skripsi.ui.penjahit.ukuran.viewmodel.UkuranViewModel
import com.fero.skripsi.utils.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson

class KategoriPenjahitFragment : BaseFragment<FragmentKategoriPenjahitBinding>() {

    private val penjahitViewModel: KategoriPenjahitViewModel by lazy {
        obtainViewModel()
    }

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
        penjahitViewModel.apply {

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

    private fun setupRvDetailKategori(data: List<DetailKategoriPenjahit>?) {
        val listKategoriAdapter = ListKategoriAdapter()
        listKategoriAdapter.setDetailKategori(data!!)

        binding.rvDetailKategori.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listKategoriAdapter
        }

        listKategoriAdapter.setOnDeleteClickCallback(object : ListKategoriAdapter.OnDeleteClickCallback{
            override fun onDeleteClicked(data: DetailKategoriPenjahit) {
                popupDelete(context, data)
            }
        })

        listKategoriAdapter.setOnUpdateClickCallback(object : ListKategoriAdapter.OnUpdateClickCallback{
            override fun onUpdateClikced(data: DetailKategoriPenjahit) {

                val editDataKategoriFragment = EditDataKategoriFragment()

                val bundle = Bundle()
                val bundleData = Gson().toJson(data)
                bundle.putString("EXTRA_DETAIL_KATEGORI", bundleData)
                editDataKategoriFragment.arguments = bundle

                val fragmentManager = childFragmentManager //cara memunculkan dialog box(1)
                editDataKategoriFragment.show(fragmentManager, EditDataKategoriFragment::class.java.simpleName) //cara memunculkan dialog box(2)
            }
        })

        listKategoriAdapter.setOnItemClickCallback(object : ListKategoriAdapter.OnItemClickCallback{
            override fun onItemClicked(data: DetailKategoriPenjahit) {
                selectedKategori(data)
            }
        })
    }

    private fun selectedKategori(data: DetailKategoriPenjahit) {
        Toast.makeText(context, "Kamu memilih " + data.nama_kategori, Toast.LENGTH_SHORT).show()
        Log.d("Test", "CLICK FROM ADAPTER")
        val intent = Intent(binding.root.context, UkuranDetailKategoriActivity::class.java)
        intent.putExtra(UkuranDetailKategoriActivity.EXTRA_DATA_KATEGORI, data)
        startActivity(intent)
    }

    private fun popupDelete(context: Context?, data: DetailKategoriPenjahit) {
        val box: Context = ContextThemeWrapper(context, R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
        val materialAlertDialogBuilder = MaterialAlertDialogBuilder(box)
        materialAlertDialogBuilder.setTitle("Hapus Data")
            .setMessage("Apa anda yakin ingin menghapus data ini?")
            .setNegativeButton("Batalkan", null)
            .setPositiveButton(
                "Hapus"
            ) { dialogInterface, i ->
                // panggil disini
                deleteDataKategori(data)
             }
            .show()
    }

    private fun deleteDataKategori(data: DetailKategoriPenjahit){
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[KategoriPenjahitViewModel::class.java]

        viewModel.apply {
            dataListDetailKategori.observe(this@KategoriPenjahitFragment, {
            })

            messageSuccess.observe(this@KategoriPenjahitFragment, {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })

            messageFailed.observe(this@KategoriPenjahitFragment, {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })
        }

        viewModel.deleteDataDetailKategori(data)

//        var message = data.nama_kategori
//        showMessage( "Data $message Berhasil dihapus", context)

    }

    fun refreshGetDataViewModel() {
        penjahitViewModel.getListDetailKategori(dataPenjahit)
    }


    private fun showMessage(message: String, context: Context?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
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