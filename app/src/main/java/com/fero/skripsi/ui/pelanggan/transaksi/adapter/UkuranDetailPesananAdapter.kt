package com.fero.skripsi.ui.pelanggan.transaksi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fero.skripsi.databinding.ItemListUkuranPesananBinding
import com.fero.skripsi.model.UkuranDetailPesanan

class UkuranDetailPesananAdapter : RecyclerView.Adapter<UkuranDetailPesananAdapter.UkuranDetailPesananViewHolder>() {

    val listUkuranPesanan = mutableListOf<UkuranDetailPesanan>()

    fun setUkuranPesanan(listUkuranPesanan: List<UkuranDetailPesanan>){
        this.listUkuranPesanan.clear()
        this.listUkuranPesanan.addAll(listUkuranPesanan)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UkuranDetailPesananViewHolder {
        val itemListUkuranPesananBinding = ItemListUkuranPesananBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UkuranDetailPesananViewHolder(itemListUkuranPesananBinding)
    }

    override fun onBindViewHolder(holder: UkuranDetailPesananViewHolder, position: Int) {
        val data = listUkuranPesanan[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listUkuranPesanan.size
    }

    inner class UkuranDetailPesananViewHolder(private var binding: ItemListUkuranPesananBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UkuranDetailPesanan){
            binding.apply {
                tvNamaUkuran.text = data.nama_ukuran
                tvUkuran.text = data.ukuran_pesanan.toString()


            }
        }
    }
}