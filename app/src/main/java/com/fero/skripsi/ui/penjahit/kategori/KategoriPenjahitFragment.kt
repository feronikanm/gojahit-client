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
import androidx.recyclerview.widget.LinearLayoutManager
import com.fero.skripsi.R
import com.fero.skripsi.core.BaseFragment
import com.fero.skripsi.databinding.FragmentKategoriPenjahitBinding
import com.fero.skripsi.model.DetailKategoriPenjahit
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.ui.penjahit.kategori.adapter.ListKategoriAdapter
import com.fero.skripsi.ui.penjahit.kategori.viewmodel.KategoriPenjahitViewModel
import com.fero.skripsi.ui.penjahit.ukuran.UkuranDetailKategoriActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson

class KategoriPenjahitFragment : BaseFragment<FragmentKategoriPenjahitBinding>() {

    private val kategoriPenjahitViewModel: KategoriPenjahitViewModel by lazy {
        obtainViewModel()
    }

    private val dataPenjahit by lazy {
        baseGetInstance<Penjahit>("EXTRA_PENJAHIT_KATEGORI")
    }

    private val kategoriAdapter by lazy {
        ListKategoriAdapter()
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentKategoriPenjahitBinding {
       return FragmentKategoriPenjahitBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
        kategoriPenjahitViewModel.apply {

            listDetailKategori.observe(viewLifecycleOwner, {
                setupRvDetailKategori(it)
            })

            eventShowProgress.observe(viewLifecycleOwner, {
                setupEventProgressView(binding.progressBar, it)
            })

            eventErrorMessage.observe(viewLifecycleOwner, {
                showToast(it)

            })

            dataListDetailKategori.observe(viewLifecycleOwner, {

            })

            messageSuccess.observe(viewLifecycleOwner, {
                showToast(it)
            })

            eventShowProgress.observe(viewLifecycleOwner, {
                setupEventProgressView(binding.progressBar, it)
            })

            onSuccessDeleteState.observe(viewLifecycleOwner, {
                if (it) {

                    deleteItemPosition.observe(viewLifecycleOwner, {
                        kategoriAdapter.deleteItem(it)
                    })

                }
            })

        }

        kategoriPenjahitViewModel.getListDetailKategori(dataPenjahit)
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
        kategoriAdapter.setDetailKategori(data!!)

        binding.rvDetailKategori.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = kategoriAdapter
        }

        kategoriAdapter.setOnDeleteClickCallback(object : ListKategoriAdapter.OnDeleteClickCallback{
            override fun onDeleteClicked(data: DetailKategoriPenjahit, position: Int) {
                popupDelete(context, data, position)
            }
        })

        kategoriAdapter.setOnUpdateClickCallback(object : ListKategoriAdapter.OnUpdateClickCallback{
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

        kategoriAdapter.setOnItemClickCallback(object : ListKategoriAdapter.OnItemClickCallback{
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

    private fun popupDelete(context: Context?, data: DetailKategoriPenjahit, position: Int) {
        val box: Context = ContextThemeWrapper(context, R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
        val materialAlertDialogBuilder = MaterialAlertDialogBuilder(box)
        materialAlertDialogBuilder.setTitle("Hapus Data")
            .setMessage("Apa anda yakin ingin menghapus data ini?")
            .setNegativeButton("Tidak", null)
            .setPositiveButton(
                "Hapus"
            ) { dialogInterface, i ->
                // panggil disini
                deleteDataKategori(data, position)
             }
            .show()
    }

    private fun deleteDataKategori(data: DetailKategoriPenjahit, position: Int){
        kategoriPenjahitViewModel.deleteDataDetailKategori(data, position)
    }

    fun refreshGetDataViewModel() {
        kategoriPenjahitViewModel.getListDetailKategori(dataPenjahit)
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