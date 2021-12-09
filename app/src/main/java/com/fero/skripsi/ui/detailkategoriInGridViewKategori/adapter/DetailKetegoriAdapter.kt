package com.fero.skripsi.ui.detailkategoriInGridViewKategori.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fero.skripsi.databinding.ItemListPenjahitBinding
import com.fero.skripsi.databinding.ItemListPenjahitKategoriBinding
import com.fero.skripsi.model.DetailKategoriNilai
import com.fero.skripsi.model.Pelanggan
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.utils.Constant
import java.text.DecimalFormat

class DetailKetegoriAdapter : RecyclerView.Adapter<DetailKetegoriAdapter.DetailKategoriViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    var listPenjahit = mutableListOf<DetailKategoriNilai>()
    private lateinit var dataPelanggan: Pelanggan
    private lateinit var dataPenjahit: Penjahit

    fun setupDataPenjahit(data: Penjahit) {
        dataPenjahit = data
    }

    fun setupDataPelanggan(data: Pelanggan) {
        dataPelanggan = data
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setPenjahitByKategori(penjahit: List<DetailKategoriNilai>) {
        this.listPenjahit.clear()
        this.listPenjahit.addAll(penjahit)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailKategoriViewHolder {
        val itemListPenjahitKategoriBinding = ItemListPenjahitKategoriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailKategoriViewHolder(itemListPenjahitKategoriBinding)
    }

    override fun onBindViewHolder(
        holder: DetailKategoriViewHolder,
        position: Int
    ) {
        val data = listPenjahit[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listPenjahit.size
    }

    inner class DetailKategoriViewHolder(private var binding: ItemListPenjahitKategoriBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DetailKategoriNilai) {
            binding.apply {
                tvNamaToko.text = data.nama_toko
                tvNamaPenjahit.text = data.nama_penjahit
                val df = DecimalFormat("#.#")
                val extraRating = data.nilai_akhir
                val rating = df.format(extraRating)
                tvRating.text = rating.toString()

                Glide.with(itemView.context)
                    .load("${Constant.IMAGE_PENJAHIT}${data.foto_penjahit}")
                    .into(imgProfile)

                root.setOnClickListener {

                    onItemClickCallback.onItemClicked(listPenjahit[adapterPosition])

//                    Toast.makeText(root.context, "Kamu memilih " + data.nama_penjahit, Toast.LENGTH_SHORT).show()
//                    Log.d("Test", "CLICK FROM ADAPTER")
//                    val intent = Intent(binding.root.context, DetailPenjahitPelangganActivity::class.java)
//                    intent.putExtra(DetailPenjahitPelangganActivity.EXTRA_DATA_PENJAHIT, data)
//                    root.context.startActivity(intent)
                }

            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DetailKategoriNilai)
    }
}