package com.fero.skripsi.ui.penjahit.kategori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fero.skripsi.databinding.ItemListKategoriBinding
import com.fero.skripsi.model.DetailKategori
import com.fero.skripsi.utils.Constant

class ListKategoriAdapter : RecyclerView.Adapter<ListKategoriAdapter.ListKategoriViewHolder>() {

    var listDetailKategori = mutableListOf<DetailKategori>()

    fun setDetailKategori(kategori: List<DetailKategori>){
        this.listDetailKategori.clear()
        this.listDetailKategori.addAll(kategori)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListKategoriViewHolder {
        val itemListKategoriBinding = ItemListKategoriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListKategoriViewHolder(itemListKategoriBinding)
    }

    override fun onBindViewHolder(holder: ListKategoriViewHolder, position: Int) {
        val data = listDetailKategori[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listDetailKategori.size
    }

    inner class ListKategoriViewHolder(private var binding: ItemListKategoriBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DetailKategori){
            binding.apply {

                tvKategori.text = data.nama_kategori

                Glide.with(itemView.context)
                    .load("${Constant.IMAGE_KATEGORI}${data.gambar_kategori}")
                    .into(imgKategori)

                itemView.setOnClickListener {

                }
            }
        }
    }
}