package com.fero.skripsi.ui.pelanggan.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fero.skripsi.databinding.ItemListShowKategoriBinding
import com.fero.skripsi.model.DetailKategoriNilai
import com.fero.skripsi.model.DetailKategoriPenjahit
import com.fero.skripsi.utils.Constant

class ListKategoriInDetailAdapter : RecyclerView.Adapter<ListKategoriInDetailAdapter.ListKategoriInDetailViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    var listDetailKategori = mutableListOf<DetailKategoriNilai>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setDetailKategori(kategori: List<DetailKategoriNilai>){
        this.listDetailKategori.clear()
        this.listDetailKategori.addAll(kategori)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListKategoriInDetailViewHolder {
        val itemListShowKategoriBinding = ItemListShowKategoriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListKategoriInDetailViewHolder(itemListShowKategoriBinding)
    }

    override fun onBindViewHolder(holder: ListKategoriInDetailViewHolder, position: Int) {
        val data = listDetailKategori[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listDetailKategori.size
    }

    inner class ListKategoriInDetailViewHolder(private var binding: ItemListShowKategoriBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DetailKategoriNilai){
            binding.apply {
                tvKategori.text = data.nama_kategori
                Glide.with(itemView.context)
                    .load("${Constant.IMAGE_KATEGORI}${data.gambar_kategori}")
                    .into(imgKategori)

                root.setOnClickListener {
                    onItemClickCallback.onItemClicked(listDetailKategori[adapterPosition])
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DetailKategoriNilai)
    }
}