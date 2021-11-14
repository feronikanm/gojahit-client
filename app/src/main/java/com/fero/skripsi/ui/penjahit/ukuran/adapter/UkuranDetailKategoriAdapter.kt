package com.fero.skripsi.ui.penjahit.ukuran.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fero.skripsi.databinding.ItemListUkuranDetailKategoriBinding
import com.fero.skripsi.model.UkuranDetailKategori
import com.fero.skripsi.utils.Constant

class UkuranDetailKategoriAdapter : RecyclerView.Adapter<UkuranDetailKategoriAdapter.UkuranDetailKategoriViewHolder>(){

    var listUkuran = mutableListOf<UkuranDetailKategori>()

    fun setUkuranDetailKategori(listUkuran: List<UkuranDetailKategori>){
        this.listUkuran.clear()
        this.listUkuran.addAll(listUkuran)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UkuranDetailKategoriViewHolder {
        val itemListUkuranBinding = ItemListUkuranDetailKategoriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UkuranDetailKategoriViewHolder(itemListUkuranBinding)
    }

    override fun onBindViewHolder(holderDetailKategori: UkuranDetailKategoriViewHolder, position: Int) {
        val data = listUkuran[position]
        holderDetailKategori.bind(data)
    }

    override fun getItemCount(): Int {
        return listUkuran.size
    }

    inner class UkuranDetailKategoriViewHolder(private var binding: ItemListUkuranDetailKategoriBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data: UkuranDetailKategori){
            binding.apply {
                tvUkuran.text = data.nama_ukuran

                Glide.with(root.context)
                    .load("${Constant.IMAGE_UKURAN}${data.gambar_ukuran}")
                    .into(imgUkuran)

            }
        }

    }

}