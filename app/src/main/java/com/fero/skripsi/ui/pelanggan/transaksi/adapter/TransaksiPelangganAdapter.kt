package com.fero.skripsi.ui.pelanggan.transaksi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fero.skripsi.databinding.ItemListTransaksiPelangganBinding
import com.fero.skripsi.model.DetailKategoriPenjahit
import com.fero.skripsi.model.Pesanan

class TransaksiPelangganAdapter : RecyclerView.Adapter<TransaksiPelangganAdapter.TransaksiPelangganViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private lateinit var onDeleteClickCallback: OnDeleteClickCallback
    private lateinit var onUpdateClickCallback: OnUpdateClickCallback
    val listPesanan = mutableListOf<Pesanan>()


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setOnDeleteClickCallback(onDeleteClickCallback: OnDeleteClickCallback){
        this.onDeleteClickCallback = onDeleteClickCallback
    }

    fun setOnUpdateClickCallback(onUpdateClickCallback: OnUpdateClickCallback){
        this.onUpdateClickCallback = onUpdateClickCallback
    }

    fun setListPesanan(listPesanan: List<Pesanan>){
        this.listPesanan.clear()
        this.listPesanan.addAll(listPesanan)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransaksiPelangganViewHolder {
        val itemListTransaksiPelangganBinding = ItemListTransaksiPelangganBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransaksiPelangganViewHolder(itemListTransaksiPelangganBinding)
    }

    override fun onBindViewHolder(holder: TransaksiPelangganViewHolder, position: Int) {
        val data = listPesanan[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listPesanan.size
    }

    inner class TransaksiPelangganViewHolder(private var binding: ItemListTransaksiPelangganBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Pesanan) {
            binding.apply {

                tvIdPesanan.text = "Kode Pesanan : " + data.id_pesanan.toString()
                tvTanggalSelesai.text = "Tanggal Selesai : " + data.tanggal_pesanan_selesai
                tvStatusPesanan.text = "Status Pesanan : " + data.status_pesanan

//                btnDelete.setOnClickListener {
//                    onDeleteClickCallback.onDeleteClicked(listPesanan[adapterPosition])
//                }

                btnEdit.setOnClickListener {
                    onUpdateClickCallback.onUpdateClikced(listPesanan[adapterPosition])
                }

                root.setOnClickListener {
                    onItemClickCallback.onItemClicked(listPesanan[adapterPosition])

                }

            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Pesanan)
    }

    interface OnDeleteClickCallback{
        fun onDeleteClicked(data: Pesanan)
    }

    interface OnUpdateClickCallback{
        fun onUpdateClikced(data: Pesanan)
    }

}