package com.fero.skripsi.ui.penjahit.kategori

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fero.skripsi.R
import com.fero.skripsi.databinding.ItemListKategoriBinding
import com.fero.skripsi.model.ListDetailKategori
import com.fero.skripsi.utils.Constant
import com.fero.skripsi.utils.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ListKategoriAdapter : RecyclerView.Adapter<ListKategoriAdapter.ListKategoriViewHolder>() {

    var listDetailKategori = mutableListOf<ListDetailKategori>()


    fun setDetailKategori(kategoriList: List<ListDetailKategori>){
        this.listDetailKategori.clear()
        this.listDetailKategori.addAll(kategoriList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListKategoriViewHolder {
        val itemListKategoriBinding = ItemListKategoriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListKategoriViewHolder(itemListKategoriBinding)
    }

    override fun onBindViewHolder(holder: ListKategoriViewHolder, position: Int) {
        val data = listDetailKategori[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listDetailKategori.size
    }

    inner class ListKategoriViewHolder(private var binding: ItemListKategoriBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ListDetailKategori){
            binding.apply {

                tvKategori.text = data.nama_kategori

                binding.btnDelete.setOnClickListener {
                    popupDelete(root.context)
//                    deleteData(root.context, data)
                }

                Glide.with(root.context)
                    .load("${Constant.IMAGE_KATEGORI}${data.gambar_kategori}")
                    .into(imgKategori)

                root.setOnClickListener {

                }
            }
        }
    }


    private fun popupDelete(context: Context) {
        val box: Context = ContextThemeWrapper(context, R.style.AppTheme)
        val materialAlertDialogBuilder = MaterialAlertDialogBuilder(box)
        materialAlertDialogBuilder.setTitle("Hapus Data")
            .setMessage("Apa anda yakin ingin menghapus data ini?")
            .setNegativeButton("Batalkan", null)
            .setPositiveButton(
                "Hapus"
            ) { dialogInterface, i -> {
                // panggil disini
                showMessage( "Data Berhasil dihapus", context)
            } }
            .show()
    }


    private fun showMessage(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


}