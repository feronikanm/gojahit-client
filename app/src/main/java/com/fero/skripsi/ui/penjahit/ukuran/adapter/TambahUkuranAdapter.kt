package com.fero.skripsi.ui.penjahit.ukuran.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fero.skripsi.databinding.ItemListUkuranBinding
import com.fero.skripsi.model.UkuranDetailKategori
import com.fero.skripsi.utils.Constant

class TambahUkuranAdapter : RecyclerView.Adapter<TambahUkuranAdapter.TambahUkuranViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback
    val listUkuran = mutableListOf<UkuranDetailKategori>()

    fun setItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setUkuran(listUkuran: List<UkuranDetailKategori>){
        this.listUkuran.clear()
        this.listUkuran.addAll(listUkuran)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TambahUkuranViewHolder {
        val itemListUkuranBinding = ItemListUkuranBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TambahUkuranViewHolder(itemListUkuranBinding)
    }

    override fun onBindViewHolder(holder: TambahUkuranViewHolder, position: Int) {
        val data = listUkuran[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listUkuran.size
    }

    inner class TambahUkuranViewHolder(private var binding: ItemListUkuranBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UkuranDetailKategori){
            binding.apply {
                tvUkuran.text = data.nama_ukuran

                Glide.with(root.context)
                    .load("${Constant.IMAGE_UKURAN}${data.gambar_ukuran}")
                    .into(imgUkuran)

                root.setOnClickListener {
                    onItemClickCallback.onItemClicked(listUkuran[adapterPosition])
                }

            }
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: UkuranDetailKategori)
    }


}