package com.fero.skripsi.ui.pelanggan.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fero.skripsi.databinding.ItemCardKategoriBinding
import com.fero.skripsi.model.DetailKategoriNilai
import com.fero.skripsi.utils.Constant

class KategoriPenjahitAdapter : RecyclerView.Adapter<KategoriPenjahitAdapter.KategoriPenjahitViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    var listKategori = mutableListOf<DetailKategoriNilai>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setKategori(kategori: List<DetailKategoriNilai>){
        this.listKategori.clear()
        this.listKategori.addAll(kategori)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): KategoriPenjahitViewHolder {
        val itemCardKategoriBinding = ItemCardKategoriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KategoriPenjahitViewHolder(itemCardKategoriBinding)
    }

    override fun onBindViewHolder(
        holder: KategoriPenjahitViewHolder,
        position: Int
    ) {
        val data = listKategori[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listKategori.size
    }

    inner class KategoriPenjahitViewHolder(private var binding: ItemCardKategoriBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DetailKategoriNilai){
            binding.apply {
                tvNamaKategori.text = data.nama_kategori
                Glide.with(itemView.context)
                    .load("${Constant.IMAGE_KATEGORI}${data.gambar_kategori}")
                    .into(ivGambarKategori)

                root.setOnClickListener {
                    onItemClickCallback.onItemClicked(listKategori[adapterPosition])
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DetailKategoriNilai)
    }
}