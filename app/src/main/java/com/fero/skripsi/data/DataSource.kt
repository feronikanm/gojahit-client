package com.fero.skripsi.data

import com.fero.skripsi.data.source.ResponseCallback
import com.fero.skripsi.model.Kategori
import com.fero.skripsi.model.Nilai
import com.fero.skripsi.model.Pelanggan
import com.fero.skripsi.model.Penjahit

interface DataSource {

    fun getDataPenjahit(callback: ResponseCallback<List<Nilai>>)

    fun getDataKategori(callback: ResponseCallback<List<Kategori>>)

    fun registerPelanggan(data: Pelanggan, responseCallback: ResponseCallback<Pelanggan>)

    fun loginPelanggan(email: String, password: String, responseCallback: ResponseCallback<Pelanggan>)

    fun updatePelanggan(data: Pelanggan, responseCallback: ResponseCallback<Pelanggan>)

    fun registerPenjahit(data: Penjahit, responseCallback: ResponseCallback<Penjahit>)

    fun loginPenjahit(email: String, password: String, responseCallback: ResponseCallback<Penjahit>)

    fun updatePenjahit(data: Penjahit, responseCallback: ResponseCallback<Penjahit>)
}