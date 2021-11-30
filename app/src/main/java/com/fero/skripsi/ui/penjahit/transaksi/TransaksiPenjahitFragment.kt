package com.fero.skripsi.ui.penjahit.transaksi

import android.content.Context
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
import com.fero.skripsi.databinding.FragmentTransaksiPenjahitBinding
import com.fero.skripsi.model.Pelanggan
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.model.Pesanan
import com.fero.skripsi.ui.pelanggan.pesanan.viewmodel.PesananViewModel
import com.fero.skripsi.ui.pelanggan.transaksi.adapter.TransaksiPelangganAdapter
import com.fero.skripsi.utils.FunctionKu
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class TransaksiPenjahitFragment : BaseFragment<FragmentTransaksiPenjahitBinding>() {

    private lateinit var pesananViewModel: PesananViewModel
    private val dataPenjahit by lazy{
        baseGetInstance<Penjahit>("EXTRA_PENJAHIT")
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTransaksiPenjahitBinding {
        return FragmentTransaksiPenjahitBinding.inflate(inflater, container, false)
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
        pesananViewModel.getDataPesananByPenjahit(dataPenjahit)
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
        val box: Context = ContextThemeWrapper(context, R.style.AppTheme)
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
    }

    override fun setupUI(view: View, savedInstanceState: Bundle?) {


    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TransaksiPenjahitFragment().apply {
                arguments = Bundle().apply {}
            }
    }


}