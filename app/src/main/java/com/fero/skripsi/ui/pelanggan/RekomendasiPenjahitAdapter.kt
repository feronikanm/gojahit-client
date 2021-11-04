package com.fero.skripsi.ui.pelanggan

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fero.skripsi.databinding.ItemRowPenjahitBinding
import com.fero.skripsi.model.Nilai
import com.fero.skripsi.ui.penjahit.DetailPenjahitActivity
import com.fero.skripsi.utils.Constant

class RekomendasiPenjahitAdapter : RecyclerView.Adapter<RekomendasiPenjahitAdapter.RekomendasiPenjahitViewHolder>() {

    var listPenjahit  = mutableListOf<Nilai>()

    fun setPenjahit(penjahit : List<Nilai>){
        this.listPenjahit.clear()
        this.listPenjahit.addAll(penjahit)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RekomendasiPenjahitAdapter.RekomendasiPenjahitViewHolder {
        val itemRowPenjahitBinding = ItemRowPenjahitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RekomendasiPenjahitViewHolder(itemRowPenjahitBinding)
    }

    override fun onBindViewHolder(
        holder: RekomendasiPenjahitAdapter.RekomendasiPenjahitViewHolder,
        position: Int
    ) {
        val data = listPenjahit[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listPenjahit.size
    }

    inner class RekomendasiPenjahitViewHolder(private var binding: ItemRowPenjahitBinding) : RecyclerView.ViewHolder(binding.root)  {
        fun bind(data: Nilai){
            binding.apply {
                tvPenjahitName.text = data.nama_penjahit
                tvToko.text = data.nama_toko

                Glide.with(itemView.context)
                    .load("${Constant.IMAGE_PENJAHIT}${data.foto_penjahit}")
                    .into(imgProfile)

                itemView.setOnClickListener {
                    Log.d("Test", "CLICK FROM ADAPTER")

                    val intent = Intent(binding.root.context, DetailPenjahitPelangganActivity::class.java)

                    // Using Gson
//                val extraData = Gson().toJson(data)
//                intent.putExtra(DetailPenjahitActivity.EXTRA_PENJAHIT, extraData)

                    // Using Parcelable
                    intent.putExtra(DetailPenjahitPelangganActivity.EXTRA_DATA_PENJAHIT, data)

                    binding.root.context.startActivity(intent)
                }
            }
        }
    }
}