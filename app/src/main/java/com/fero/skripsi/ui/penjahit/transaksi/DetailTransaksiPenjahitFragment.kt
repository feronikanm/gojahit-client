package com.fero.skripsi.ui.penjahit.transaksi

import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fero.skripsi.core.BaseFragment
import com.fero.skripsi.databinding.FragmentDetailTransaksiPenjahitBinding
import com.fero.skripsi.model.Pesanan
import com.fero.skripsi.model.UkuranDetailPesanan
import com.fero.skripsi.ui.pelanggan.pesanan.viewmodel.PesananViewModel
import com.fero.skripsi.ui.pelanggan.transaksi.adapter.UkuranDetailPesananAdapter
import com.fero.skripsi.ui.pelanggan.transaksi.viewmodel.UkuranDetailPesananViewModel
import com.fero.skripsi.ui.penjahit.transaksi.adapter.UkuranDetailPesananPenjahitAdapter

class DetailTransaksiPenjahitFragment : BaseFragment<FragmentDetailTransaksiPenjahitBinding>() {

    private lateinit var pesananViewModel: PesananViewModel
    private lateinit var ukuranDetailPesananViewModel: UkuranDetailPesananViewModel
    private val extraDataPesanan by lazy{
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

            dataDetailPesanan.observe(viewLifecycleOwner, {
                binding.apply {
                    tvIdPesanan.text = "Kode Pesanan : " + it.id_pesanan.toString()
                    tvIdPenjahit.text = "ID Penjahit : " + it.id_penjahit.toString()
                    tvIdPelanggan.text = "ID Pelanggan : " + it.id_pelanggan.toString()
                    tvIdDetailKategori.text = "ID Detail Kategori : " + it.id_detail_kategori.toString()
                    tvNamaPelanggan3.text = "Nama Pelanggan : " + it.nama_pelanggan
                    tvTelpPelanggan.text = "Telp Pelanggan : " + it.telp_pelanggan
                    tvAlamatPelanggan.text = "Alamat Pelanggan : " + it.alamat_pelanggan
                    tvKategori.text = "Kategori : " + it.nama_kategori
                    tvTanggalPesanan.text = "Tanggal Pesanan : " + it.tanggal_pesanan
                    tvTanggalPesananSelesai.text = "Tanggal Pesanan Selesai : " + it.tanggal_pesanan_selesai
                    tvCatatanPesanan.text = "Catatan Pesanam : " + it.catatan_pesanan
                    tvAsalBahan.text = "Asal Bahan : " + it.asal_bahan
                    tvStatusBahan.text = "Status Bahan : " + it.status_bahan

                    tvOngkosPenjahit.text = "Ongkos Penjahit : " + it.ongkos_penjahit
                    tvJumlahJahitan.text = "Jumlah Jahitan : " + it.jumlah_jahitan
                    tvTotalBiaya.text = "Total Biaya : " + it.total_biaya
                    tvKetPesanan.text = "Keterangan : " + it.lama_waktu_pengerjaan
                    tvStatusPesanan.text = "Status Pesanan : " + it.status_pesanan

                    val asalPelanggan = "Pelanggan"
                    if (it.asal_bahan.equals(asalPelanggan)){
                        tvHargaBahan.visibility = View.GONE
                    } else {
                        tvHargaBahan.text = "Harga Bahan : " + it.harga_bahan
                    }
                }
            })
            getDataDetailPesananById(extraDataPesanan)


            dataPesanan.observe(viewLifecycleOwner, {
//                binding.apply {
//                    tvIdPesanan.text = "Kode Pesanan : " + it.id_pesanan.toString()
//                    tvIdPenjahit.text = "ID Penjahit : " + it.id_penjahit.toString()
//                    tvIdPelanggan.text = "ID Pelanggan : " + it.id_pelanggan.toString()
//                    tvIdDetailKategori.text = "ID Detail Kategori : " + it.id_detail_kategori.toString()
//                    tvTanggalPesanan.text = "Tanggal Pesanan : " + it.tanggal_pesanan
//                    tvTanggalPesananSelesai.text = "Tanggal Pesanan Selesai : " + it.tanggal_pesanan_selesai
//                    tvKetPesanan.text = "Keterangan : " + it.lama_waktu_pengerjaan
//                    tvStatusPesanan.text = "Status Pesanan : " + it.status_pesanan
//                }

                val statusBelumDiverifikasi = "Belum diverifikasi"
                val statusDiverifikasi = "Verifikasi"
                val statusTidakDiterima = "Tidak Diterima"
                val statusProses = "Sedang dikerjakan"
                val statusSelesai = "Selesai"

                val ketStatusDiverifikasi = "Selamat Pesanan Anda telah diverifikasi oleh Penjahit, Silahkan masukkan ukuran"
                val ketStatusDiProses = "Pesanan Anda sedang diproses oleh Penjahit"
                val ketStatusTidakDiterima = "Opss Maaf, Pesanan Anda tidak Diterima oleh Penjahit, Jahitan Penjahit saat ini sedang penuh, cari yang lain"
                val ketStatusUkuranTelahDimasukkan = "Data Ukuran telah dimasukkan"


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
                    binding.tvListUkuran.visibility = View.INVISIBLE
                }

                if (it.status_pesanan.equals(statusDiverifikasi)){
                    binding.layoutTerimaTolak.visibility = View.GONE
                    binding.btnProses.visibility = View.INVISIBLE
                    binding.btnSelesai.visibility = View.INVISIBLE
                    binding.tvListUkuran.visibility = View.INVISIBLE
                }

                if (it.lama_waktu_pengerjaan.equals(ketStatusUkuranTelahDimasukkan)){
                    binding.layoutTerimaTolak.visibility = View.GONE
                    binding.btnProses.visibility = View.VISIBLE
                    binding.btnSelesai.visibility = View.GONE
                    binding.tvListUkuran.visibility = View.VISIBLE
                }

                if (it.status_pesanan.equals(statusProses)){
                    binding.layoutTerimaTolak.visibility = View.GONE
                    binding.btnProses.visibility = View.GONE
                    binding.btnSelesai.visibility = View.VISIBLE
                    binding.tvListUkuran.visibility = View.VISIBLE
                }

                if (it.status_pesanan.equals(statusTidakDiterima)){
                    binding.layoutTerimaTolak.visibility = View.GONE
                    binding.btnProses.visibility = View.GONE
                    binding.btnSelesai.visibility = View.GONE
                    binding.tvListUkuran.visibility = View.GONE
                }

                if (it.status_pesanan.equals(statusSelesai)){
                    binding.layoutTerimaTolak.visibility = View.GONE
                    binding.btnProses.visibility = View.GONE
                    binding.btnSelesai.visibility = View.GONE
                    binding.tvListUkuran.visibility = View.VISIBLE
                }

                if (it.status_pesanan.equals(statusDiverifikasi)) {
                    binding.tvStatusPesanan.setTextColor(Color.GREEN)
                }

                if (it.status_pesanan.equals(statusProses)) {
                    binding.tvStatusPesanan.setTextColor(Color.GREEN)
                }

                if (it.status_pesanan.equals(statusTidakDiterima)) {
                    binding.tvStatusPesanan.setTextColor(Color.RED)
                }

                if (it.status_pesanan.equals(statusSelesai)) {
                    binding.tvStatusPesanan.setTextColor(Color.BLUE)
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
        pesananViewModel.getDataPesananById(extraDataPesanan)

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
        ukuranDetailPesananViewModel.getDataUkuranByPesanan(extraDataPesanan)
        val listUkuran = ukuranDetailPesananViewModel.getDataUkuranByPesanan(extraDataPesanan)

        Log.d("data ukuran : ", listUkuran.toString())
    }

    private fun setupRvUkuran(data: List<UkuranDetailPesanan>?) {
        val ukuranDetailPesananPenjahitAdapter = UkuranDetailPesananPenjahitAdapter()
        ukuranDetailPesananPenjahitAdapter.setUkuranPesanan(data!!)

        binding.rvUkuran.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ukuranDetailPesananPenjahitAdapter
        }
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