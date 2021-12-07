package com.fero.skripsi.ui.penjahit.transaksi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fero.skripsi.R
import com.fero.skripsi.core.BaseFragment
import com.fero.skripsi.databinding.FragmentDetailTransaksiPenjahitBinding
import com.fero.skripsi.model.Pelanggan
import com.fero.skripsi.model.Pesanan
import com.fero.skripsi.ui.pelanggan.pesanan.viewmodel.PesananViewModel
import kotlinx.android.synthetic.main.activity_detail_penjahit.*

class DetailTransaksiPenjahitFragment : BaseFragment<FragmentDetailTransaksiPenjahitBinding>() {

    private lateinit var pesananViewModel: PesananViewModel
    private val dataPesanan by lazy{
        baseGetInstance<Pesanan>("DETAIL_PESANAN_PENJAHIT")
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailTransaksiPenjahitBinding {
        return FragmentDetailTransaksiPenjahitBinding.inflate(inflater, container, false)
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

                val ketStatusDiverifikasi = "Selamat Pesanan Anda telah diverifikasi oleh Penjahit, Jahitan Anda sedang di Proses"
                val ketStatusDiProses = "Pesanan Anda sedang diproses oleh Penjahit"
                val ketStatusTidakDiterima = "Opss Maaf, Pesanan Anda Diterima oleh Penjahit, Jahitan Penjahit sedang penuh dalam waktu dekat ini"

                val pesananDiTerima = Pesanan(
                    it.id_pesanan, it.id_pelanggan, it.id_penjahit, it.id_detail_kategori, it.tanggal_pesanan, it.tanggal_pesanan_selesai,
                    ketStatusDiverifikasi,
                    it.catatan_pesanan, it.desain_jahitan, it.bahan_jahit, it.asal_bahan, it.panjang_bahan, it.lebar_bahan, it.status_bahan,
                    it.harga_bahan, it.ongkos_penjahit, it.jumlah_jahitan, it.biaya_jahitan, it.total_biaya,
                    statusDiverifikasi,
                )

                val pesananTidakDiterima = Pesanan(
                    it.id_pesanan, it.id_pelanggan, it.id_penjahit, it.id_detail_kategori, it.tanggal_pesanan, it.tanggal_pesanan_selesai,
                    ketStatusTidakDiterima,
                    it.catatan_pesanan, it.desain_jahitan, it.bahan_jahit, it.asal_bahan, it.panjang_bahan, it.lebar_bahan, it.status_bahan,
                    it.harga_bahan, it.ongkos_penjahit, it.jumlah_jahitan, it.biaya_jahitan, it.total_biaya,
                    statusTidakDiterima,
                )

                val pesananDiProses = Pesanan(
                    it.id_pesanan, it.id_pelanggan, it.id_penjahit, it.id_detail_kategori, it.tanggal_pesanan, it.tanggal_pesanan_selesai,
                    ketStatusDiProses,
                    it.catatan_pesanan, it.desain_jahitan, it.bahan_jahit, it.asal_bahan, it.panjang_bahan, it.lebar_bahan, it.status_bahan,
                    it.harga_bahan, it.ongkos_penjahit, it.jumlah_jahitan, it.biaya_jahitan, it.total_biaya,
                    statusProses,
                )

                val pesananSelesai = Pesanan(
                    it.id_pesanan, it.id_pelanggan, it.id_penjahit, it.id_detail_kategori, it.tanggal_pesanan, it.tanggal_pesanan_selesai,
                    statusSelesai,
                    it.catatan_pesanan, it.desain_jahitan, it.bahan_jahit, it.asal_bahan, it.panjang_bahan, it.lebar_bahan, it.status_bahan,
                    it.harga_bahan, it.ongkos_penjahit, it.jumlah_jahitan, it.biaya_jahitan, it.total_biaya,
                    statusSelesai,
                )

                binding.btnTerima.setOnClickListener {
                    Log.d("Pesanan Diterima : ", pesananDiTerima.toString())
                    pesananViewModel.updateDataPesanan(pesananDiTerima)
                }

                binding.btnTolak.setOnClickListener {
                    Log.d("Pesanan Ditolak : ", pesananTidakDiterima.toString())
                    pesananViewModel.updateDataPesanan(pesananTidakDiterima)
                }

                binding.btnSelesai.setOnClickListener {
                    Log.d("Pesanan Selesai : ", pesananSelesai.toString())
                    pesananViewModel.updateDataPesanan(pesananSelesai)
                }

                binding.btnProses.setOnClickListener {
                    Log.d("Pesanan Di Proses : ", pesananDiProses.toString())
                    pesananViewModel.updateDataPesanan(pesananDiProses)
                }


                if (it.status_pesanan.equals(statusBelumDiverifikasi)){
                    binding.layoutTerimaTolak.visibility = View.VISIBLE
                    binding.btnProses.visibility = View.INVISIBLE
                    binding.btnSelesai.visibility = View.INVISIBLE
                }

                if (it.status_pesanan.equals(statusDiverifikasi)){
                    binding.layoutTerimaTolak.visibility = View.GONE
                    binding.btnProses.visibility = View.VISIBLE
                    binding.btnSelesai.visibility = View.INVISIBLE
                }

                if (it.status_pesanan.equals(statusProses)){
                    binding.layoutTerimaTolak.visibility = View.GONE
                    binding.btnProses.visibility = View.GONE
                    binding.btnSelesai.visibility = View.VISIBLE
                }

                if (it.status_pesanan.equals(statusTidakDiterima)){
                    binding.layoutTerimaTolak.visibility = View.GONE
                    binding.btnProses.visibility = View.GONE
                    binding.btnSelesai.visibility = View.GONE
                }

                if (it.status_pesanan.equals(statusSelesai)){
                    binding.layoutTerimaTolak.visibility = View.GONE
                    binding.btnProses.visibility = View.GONE
                    binding.btnSelesai.visibility = View.GONE
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
    }

    override fun setupUI(view: View, savedInstanceState: Bundle?) {




    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DetailTransaksiPenjahitFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}