package com.fero.skripsi.ui.penjahit.dashboard

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fero.skripsi.databinding.ItemListPenjahitBinding
import com.fero.skripsi.model.Nilai
import com.fero.skripsi.ui.penjahit.transaksi.DetailPenjahitActivity
import com.fero.skripsi.utils.Constant

class PenjahitAdapter : RecyclerView.Adapter<PenjahitAdapter.PenjahitViewHolder>() {

    var listPenjahit  = mutableListOf<Nilai>()

    fun setPenjahit(penjahit : List<Nilai>){
//        if (penjahit == null) return
        this.listPenjahit.clear()
        this.listPenjahit.addAll(penjahit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PenjahitViewHolder {
        val itemListPenjahitBinding = ItemListPenjahitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PenjahitViewHolder(itemListPenjahitBinding)
    }

    override fun onBindViewHolder(holder: PenjahitViewHolder, position: Int) {
        val data = listPenjahit[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listPenjahit.size
    }

    inner class PenjahitViewHolder(private var binding: ItemListPenjahitBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Nilai){
            binding.apply {
                tvNamaToko.text = data.nama_toko
                tvNamaPenjahit.text = data.nama_penjahit
                tvRating.text = " " + data.nilai_akhir.toString()

                Glide.with(itemView.context)
                    .load("${Constant.IMAGE_PENJAHIT}${data.foto_penjahit}")
                    .into(imgProfile)

                itemView.setOnClickListener {
                    Log.d("Test", "CLICK FROM ADAPTER")

//                Toast.makeText(binding.root.context, "Hai ", Toast.LENGTH_SHORT).show()
                    val intent = Intent(binding.root.context, DetailPenjahitActivity::class.java)

                    // Using Gson
//                val extraData = Gson().toJson(data)
//                intent.putExtra(DetailPenjahitActivity.EXTRA_PENJAHIT, extraData)

                    // Using Parcelable
                    intent.putExtra(DetailPenjahitActivity.EXTRA_PENJAHIT, data)

                    binding.root.context.startActivity(intent)
                }
            }
        }
    }
}