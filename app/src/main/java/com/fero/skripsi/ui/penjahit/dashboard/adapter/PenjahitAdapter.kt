package com.fero.skripsi.ui.penjahit.dashboard.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fero.skripsi.databinding.ItemListPenjahitBinding
import com.fero.skripsi.model.DetailKategoriNilai
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.ui.penjahit.dashboard.DetailPenjahitActivity
import com.fero.skripsi.utils.Constant
import java.text.DecimalFormat

class PenjahitAdapter : RecyclerView.Adapter<PenjahitAdapter.PenjahitViewHolder>() {

    var listPenjahit  = mutableListOf<DetailKategoriNilai>()
    private lateinit var dataPenjahit: Penjahit

    //mendapatkan extraData penjahit dari dashboard
    fun setupDataPenjahit(data: Penjahit) {
        dataPenjahit = data
    }

    fun setPenjahit(penjahit : List<DetailKategoriNilai>){
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
        fun bind(data: DetailKategoriNilai){
            binding.apply {
                tvNamaToko.text = data.nama_toko
                tvNamaPenjahit.text = data.nama_penjahit
                val df = DecimalFormat("#.#")
                val extraRating = data.nilai_akhir
                val rating = df.format(extraRating)
//                tvRating.text = rating.toString()
                tvRating.text = extraRating.toString()
                tvJarak.text = " " + getHasilOlahDataLongLat(dataPenjahit, data) + " km"

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
                    intent.putExtra(DetailPenjahitActivity.EXTRA_DATA_NILAI, data)
                    intent.putExtra(DetailPenjahitActivity.EXTRA_DATA_PENJAHIT, dataPenjahit)

                    binding.root.context.startActivity(intent)
                }
            }
        }
    }

    private fun getHasilOlahDataLongLat(dataPenjahit: Penjahit, dataNilai: DetailKategoriNilai): String {

        val lat1 = dataPenjahit.latitude_penjahit
        val long1 = dataPenjahit.longitude_penjahit
        val lat2 = dataNilai.latitude_penjahit
        val long2 = dataNilai.longitude_penjahit

        Log.d("Lat1 : ", lat1.toString())
        Log.d("Long1 : ", long1.toString())
        Log.d("Lat2 : ", lat2.toString())
        Log.d("Long2 : ", long2.toString())

        if (lat1 != null && long2 != null){
            val lati1 : Double = lat1!!.toDouble()
            val longi1 : Double = long1!!.toDouble()
            val lati2 : Double = lat2!!.toDouble()
            val longi2 : Double = long2!!.toDouble()

            Log.d("Lati1 : ", lati1.toString())
            Log.d("Longi1 : ", longi1.toString())
            Log.d("Lati2 : ", lati2.toString())
            Log.d("Longi2 : ", longi2.toString())

            val longDiff : Double = longi1 - longi2
            Log.d("Different Longitude", longDiff.toString())

            var distance: Double = (Math.sin(deg2rad(lati1)) * Math.sin(deg2rad(lati2))) + (Math.cos(deg2rad(lati1)) * Math.cos(deg2rad(lati2)) * Math.cos(deg2rad(longDiff)))

            distance = Math.acos(distance)
            distance = rad2deg(distance)
            distance = distance * 60 * 1.1515
            distance = distance * 1.609344
            Log.d("in km : ", distance.toString())

            val df = DecimalFormat("#.#")
            Log.d("with decimal format: ", (df.format(distance)).toString())

            return (df.format(distance)).toString()
        } else{

            var hasil = 0

            return hasil.toString()
        }

    }

    private fun deg2rad(lat: Double): Double {
        return (lat * Math.PI / 180.0)
    }

    private fun rad2deg(distance: Double): Double {
        return (distance * 180.0 / Math.PI)
    }
}