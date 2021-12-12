package com.fero.skripsi.ui.pelanggan.transaksi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fero.skripsi.databinding.ItemAddUkuranPesananBinding
import com.fero.skripsi.model.UkuranDetailPesanan
import com.fero.skripsi.utils.Constant

class TambahUkuranDetailPesananAdapter :
    RecyclerView.Adapter<TambahUkuranDetailPesananAdapter.TambahUkuranDetailPesananViewHolder>() {

    val listUkuranPesanan = mutableListOf<UkuranDetailPesanan>()

    fun setUkuranPesanan(listUkuranPesanan: List<UkuranDetailPesanan>) {
        this.listUkuranPesanan.clear()
        this.listUkuranPesanan.addAll(listUkuranPesanan)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TambahUkuranDetailPesananViewHolder {
        val itemAddUkuranPesananBinding =
            ItemAddUkuranPesananBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TambahUkuranDetailPesananViewHolder(itemAddUkuranPesananBinding)

    }

    override fun onBindViewHolder(holder: TambahUkuranDetailPesananViewHolder, position: Int) {
        val data = listUkuranPesanan[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listUkuranPesanan.size
    }

    inner class TambahUkuranDetailPesananViewHolder(private var binding: ItemAddUkuranPesananBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val tvNamaUkuran = binding.tvNamaUkuran
        val etUkuran = binding.etUkuran

        fun getEtUkuran(): Int {
            return if (etUkuran.text.toString() == "") {
                0
            } else {
                etUkuran.text.toString().toInt()
            }
        }

        fun bind(data: UkuranDetailPesanan) {
            tvNamaUkuran.text = data.nama_ukuran

            binding.apply {
                Glide.with(root.context)
                    .load("${Constant.IMAGE_UKURAN}${data.gambar_ukuran}")
                    .into(imgUkuran)
            }
        }
    }
}