package com.fero.skripsi.ui.pelanggan.transaksi

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fero.skripsi.R
import com.fero.skripsi.core.BaseFragment
import com.fero.skripsi.databinding.FragmentTransaksiPelangganBinding
import com.fero.skripsi.model.Pelanggan
import com.fero.skripsi.model.Pesanan
import com.fero.skripsi.ui.pelanggan.pesanan.viewmodel.PesananViewModel
import com.fero.skripsi.ui.pelanggan.transaksi.adapter.TransaksiPelangganAdapter
import com.fero.skripsi.ui.penjahit.transaksi.DetailTransaksiPenjahitFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson

class TransaksiPelangganFragment : BaseFragment<FragmentTransaksiPelangganBinding>() {

    private lateinit var pesananViewModel: PesananViewModel
    private val dataPelanggan by lazy{
        baseGetInstance<Pelanggan>("EXTRA_PELANGGAN")
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTransaksiPelangganBinding {
        return FragmentTransaksiPelangganBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
        pesananViewModel = obtainViewModel<PesananViewModel>().apply {
            listPesanan.observe(viewLifecycleOwner, {
                setupRvPesanan(it)
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
        pesananViewModel.getDataPesananByPelanggan(dataPelanggan)
    }

    private fun setupRvPesanan(data: List<Pesanan>?) {

        val transaksiPelangganAdapter = TransaksiPelangganAdapter()
        transaksiPelangganAdapter.setListPesanan(data!!)

        binding.rvPesanan.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transaksiPelangganAdapter
        }

        transaksiPelangganAdapter.setOnDeleteClickCallback(object : TransaksiPelangganAdapter.OnDeleteClickCallback{
            override fun onDeleteClicked(data: Pesanan) {
                popupDelete(context, data)
            }

        })

        transaksiPelangganAdapter.setOnUpdateClickCallback(object : TransaksiPelangganAdapter.OnUpdateClickCallback{
            override fun onUpdateClikced(data: Pesanan) {
                Toast.makeText(context, "Kamu mengupdate " + data.id_pesanan, Toast.LENGTH_SHORT).show()

            }

        })

        transaksiPelangganAdapter.setOnItemClickCallback(object : TransaksiPelangganAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Pesanan) {
                selectedPesanan(data)
            }

        })

    }

    private fun popupDelete(context: Context?, data: Pesanan) {
        val box: Context = ContextThemeWrapper(context, R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
        val materialAlertDialogBuilder = MaterialAlertDialogBuilder(box)
        materialAlertDialogBuilder.setTitle("Hapus Data")
            .setMessage("Apa anda yakin ingin membatalkan data ini?")
            .setNegativeButton("Batalkan", null)
            .setPositiveButton(
                "Hapus"
            ) { dialogInterface, i ->
                // panggil disini
                deleteDataPesanan(data)
            }
            .show()
    }

    private fun deleteDataPesanan(data: Pesanan) {
        Toast.makeText(context, "Kamu menghapus " + data.id_pesanan, Toast.LENGTH_SHORT).show()
    }

    private fun selectedPesanan(data: Pesanan) {
        Toast.makeText(context, "Kamu memilih " + data.id_pesanan, Toast.LENGTH_SHORT).show()

        val detailTransaksiPelangganFragment = DetailTransaksiPelangganFragment()

        val bundle = Bundle()
        val bundleData = Gson().toJson(data)
        bundle.putString("DETAIL_PESANAN_PELANGGAN", bundleData)

        detailTransaksiPelangganFragment.arguments = bundle

        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, detailTransaksiPelangganFragment, DetailTransaksiPelangganFragment::class.java.simpleName)
            addToBackStack(null)
            commit()
        }
    }

    override fun setupUI(view: View, savedInstanceState: Bundle?) {

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TransaksiPelangganFragment().apply {
                arguments = Bundle().apply {}
            }
    }

}