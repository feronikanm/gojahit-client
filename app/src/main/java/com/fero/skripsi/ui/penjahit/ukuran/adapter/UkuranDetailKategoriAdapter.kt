package com.fero.skripsi.ui.penjahit.ukuran.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fero.skripsi.databinding.ItemListUkuranDetailKategoriBinding
import com.fero.skripsi.model.UkuranDetailKategori
import com.fero.skripsi.utils.Constant

class UkuranDetailKategoriAdapter :
    RecyclerView.Adapter<UkuranDetailKategoriAdapter.UkuranDetailKategoriViewHolder>() {

    private lateinit var onDeleteClickCallback: OnDeleteClickCallback
    var listUkuran = mutableListOf<UkuranDetailKategori>()

    fun setOnDeleteClickCallback(onDeleteClickCallback: OnDeleteClickCallback) {
        this.onDeleteClickCallback = onDeleteClickCallback
    }

    fun setUkuranDetailKategori(listUkuran: List<UkuranDetailKategori>) {
        this.listUkuran.clear()
        this.listUkuran.addAll(listUkuran)
    }

    fun deleteItem(position: Int) {
        Log.d("Position Removed", position.toString())
        this.listUkuran.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UkuranDetailKategoriViewHolder {
        val itemListUkuranDetailKategoriBinding = ItemListUkuranDetailKategoriBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UkuranDetailKategoriViewHolder(itemListUkuranDetailKategoriBinding)
    }

    override fun onBindViewHolder(holder: UkuranDetailKategoriViewHolder, position: Int) {
        val data = listUkuran[position]
        holder.bind(data, position)
    }

    override fun getItemCount(): Int {
        return listUkuran.size
    }

    inner class UkuranDetailKategoriViewHolder(private var binding: ItemListUkuranDetailKategoriBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UkuranDetailKategori, position: Int) {
            binding.apply {
                tvUkuran.text = data.nama_ukuran

                Glide.with(root.context)
                    .load("${Constant.IMAGE_UKURAN}${data.gambar_ukuran}")
                    .into(imgUkuran)

                btnDelete.setOnClickListener {
                    onDeleteClickCallback.onDeleteClicked(data, position)
                }

            }
        }
    }

    interface OnDeleteClickCallback {
        fun onDeleteClicked(data: UkuranDetailKategori, position: Int)
    }

}