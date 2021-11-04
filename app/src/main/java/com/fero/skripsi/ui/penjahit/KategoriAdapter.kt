package com.fero.skripsi.ui.penjahit

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fero.skripsi.databinding.ItemCardKategoriBinding
import com.fero.skripsi.model.Kategori
import com.fero.skripsi.utils.Constant

class KategoriAdapter : RecyclerView.Adapter<KategoriAdapter.KategoriViewHolder>() {

    var listKategori = mutableListOf<Kategori>()

    fun setKategori(kategori: List<Kategori>){
//        if (kategori == null) return
        this.listKategori.clear()
        this.listKategori.addAll(kategori)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriViewHolder {
        val itemCardKategoriBinding = ItemCardKategoriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KategoriViewHolder(itemCardKategoriBinding)
    }

    override fun onBindViewHolder(holder: KategoriViewHolder, position: Int) {
        val data = listKategori[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listKategori.size
    }

    inner class KategoriViewHolder(private var binding: ItemCardKategoriBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Kategori){
            binding.apply {
                tvNamaKategori.text = data.nama_kategori
                Glide.with(itemView.context)
                    .load("${Constant.IMAGE_KATEGORI}${data.gambar_kategori}")
                    .into(ivGambarKategori)

                itemView.setOnClickListener {

                }
            }
        }
    }
}