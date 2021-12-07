package com.fero.skripsi.ui.pelanggan.transaksi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fero.skripsi.R
import com.fero.skripsi.core.BaseFragment
import com.fero.skripsi.databinding.FragmentDetailTransaksiPelangganBinding
import com.fero.skripsi.model.Pesanan
import com.fero.skripsi.model.UkuranDetailPesanan
import com.fero.skripsi.ui.pelanggan.pesanan.viewmodel.PesananViewModel
import com.fero.skripsi.ui.pelanggan.transaksi.adapter.TambahUkuranDetailPesananAdapter
import com.fero.skripsi.ui.pelanggan.transaksi.adapter.UkuranDetailPesananAdapter
import com.fero.skripsi.ui.pelanggan.transaksi.viewmodel.UkuranDetailPesananViewModel
import com.fero.skripsi.ui.penjahit.transaksi.DetailTransaksiPenjahitFragment
import com.google.gson.Gson


class DetailTransaksiPelangganFragment : BaseFragment<FragmentDetailTransaksiPelangganBinding>() {

    private lateinit var pesananViewModel: PesananViewModel
    private lateinit var ukuranDetailPesananViewModel: UkuranDetailPesananViewModel
    private val dataPesanan by lazy{
        baseGetInstance<Pesanan>("DETAIL_PESANAN_PELANGGAN")
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailTransaksiPelangganBinding {
        return FragmentDetailTransaksiPelangganBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
        pesananViewModel = obtainViewModel<PesananViewModel>().apply {
            dataPesanan.observe(viewLifecycleOwner, {
                binding.apply {
                    tvIdPesanan.text = "Kode Pesanan : " + it.id_pesanan.toString()
                    tvIdPenjahit.text = "ID Penjahit : " + it.id_penjahit.toString()
                    tvIdPelanggan.text = "ID Pelanggan : " + it.id_pelanggan.toString()
                    tvIdDetailKategori.text = "ID Detail Kategori : " + it.id_detail_kategori.toString()
                    tvTanggalPesanan.text = "Tanggal Pesanan : " + it.tanggal_pesanan
                    tvTanggalPesananSelesai.text = "Tanggal Pesanan Selesai : " + it.tanggal_pesanan_selesai
                    tvKetPesanan.text = "Keterangan : " + it.lama_waktu_pengerjaan
                    tvStatusPesanan.text = "Status Pesanan : " + it.status_pesanan
                }

                val statusBelumDiverifikasi = "Belum diverifikasi"
                val statusDiverifikasi = "Verifikasi"
                val statusTidakDiterima = "Tidak Diterima"
                val statusProses = "Sedang dikerjakan"
                val statusSelesai = "Selesai"

                if (it.status_pesanan.equals(statusBelumDiverifikasi)){
                    binding.btnInputUkuran.visibility = View.INVISIBLE
                    binding.tvListUkuran.visibility = View.INVISIBLE
                    binding.btnRatingPenjahit.visibility = View.GONE
                }

                if (it.status_pesanan.equals(statusDiverifikasi)){
                    binding.btnInputUkuran.visibility = View.VISIBLE
                    binding.tvListUkuran.visibility = View.VISIBLE
                    binding.btnRatingPenjahit.visibility = View.GONE
                }

                if (it.status_pesanan.equals(statusTidakDiterima)){
                    binding.btnInputUkuran.visibility = View.GONE
                    binding.tvListUkuran.visibility = View.GONE
                    binding.btnRatingPenjahit.visibility = View.GONE
                }

                if (it.status_pesanan.equals(statusProses)){
                    binding.btnInputUkuran.visibility = View.GONE
                    binding.tvListUkuran.visibility = View.GONE
                    binding.btnRatingPenjahit.visibility = View.GONE
                }

                if (it.status_pesanan.equals(statusSelesai)){
                    binding.btnInputUkuran.visibility = View.GONE
                    binding.tvListUkuran.visibility = View.VISIBLE
                    binding.btnRatingPenjahit.visibility = View.VISIBLE
                }


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
        pesananViewModel.getDataPesananById(dataPesanan)

        ukuranDetailPesananViewModel = obtainViewModel<UkuranDetailPesananViewModel>().apply {
            listUkuranDetailPesanan.observe(viewLifecycleOwner, {
                setupRvUkuran(it)
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
        ukuranDetailPesananViewModel.getDataUkuranByPesanan(dataPesanan)
        val listUkuran = ukuranDetailPesananViewModel.getDataUkuranByPesanan(dataPesanan)

        Log.d("data ukuran : ", listUkuran.toString() )

    }

    private fun setupRvUkuran(data: List<UkuranDetailPesanan>?) {
        val ukuranDetailPesananAdapter = UkuranDetailPesananAdapter()
        ukuranDetailPesananAdapter.setUkuranPesanan(data!!)

        binding.rvUkuran.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ukuranDetailPesananAdapter
        }
    }

    override fun setupUI(view: View, savedInstanceState: Bundle?) {
        binding.btnInputUkuran.setOnClickListener {

            val tambahUkuranDetailPesananFragment = TambahUkuranDetailPesananFragment()

            val bundle = Bundle()
            val bundleData = Gson().toJson(dataPesanan)
            bundle.putString("DETAIL_UKURAN_PESANAN_PENJAHIT", bundleData)
            tambahUkuranDetailPesananFragment.arguments = bundle

            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, tambahUkuranDetailPesananFragment, TambahUkuranDetailPesananFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DetailTransaksiPelangganFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}