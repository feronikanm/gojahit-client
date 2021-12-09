package com.fero.skripsi.ui.penjahit.transaksi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fero.skripsi.databinding.ItemListUkuranPesananBinding
import com.fero.skripsi.databinding.ItemListUkuranPesananPenjahitBinding
import com.fero.skripsi.model.UkuranDetailPesanan
import com.fero.skripsi.utils.Constant

class UkuranDetailPesananPenjahitAdapter : RecyclerView.Adapter<UkuranDetailPesananPenjahitAdapter.UkuranDetailPesananViewHolder>() {

    val listUkuranPesanan = mutableListOf<UkuranDetailPesanan>()

    fun setUkuranPesanan(listUkuranPesanan: List<UkuranDetailPesanan>){
        this.listUkuranPesanan.clear()
        this.listUkuranPesanan.addAll(listUkuranPesanan)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UkuranDetailPesananViewHolder {
        val itemListUkuranPesananPenjahitBinding = ItemListUkuranPesananPenjahitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UkuranDetailPesananViewHolder(itemListUkuranPesananPenjahitBinding)
    }

    override fun onBindViewHolder(holder: UkuranDetailPesananViewHolder, position: Int) {
        val data = listUkuranPesanan[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listUkuranPesanan.size
    }

    inner class UkuranDetailPesananViewHolder(private var binding: ItemListUkuranPesananPenjahitBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UkuranDetailPesanan){
            binding.apply {
                tvNamaUkuran.text = data.nama_ukuran
                tvUkuran.text = data.ukuran_pesanan.toString()

                Glide.with(root.context)
                    .load("${Constant.IMAGE_UKURAN}${data.gambar_ukuran}")
                    .into(imgUkuran)

            }
        }
    }
}