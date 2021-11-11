package com.fero.skripsi.data

import com.fero.skripsi.data.source.ResponseCallback
import com.fero.skripsi.model.*

interface DataSource {

    fun getDataPenjahit(callback: ResponseCallback<List<Nilai>>)

    fun getDataKategori(callback: ResponseCallback<List<Kategori>>)

    fun registerPelanggan(data: Pelanggan, responseCallback: ResponseCallback<Pelanggan>)

    fun loginPelanggan(email: String, password: String, responseCallback: ResponseCallback<Pelanggan>)

    fun updatePelanggan(data: Pelanggan, responseCallback: ResponseCallback<Pelanggan>)

    fun registerPenjahit(data: Penjahit, responseCallback: ResponseCallback<Penjahit>)

    fun loginPenjahit(email: String, password: String, responseCallback: ResponseCallback<Penjahit>)

    fun updatePenjahit(data: Penjahit, responseCallback: ResponseCallback<Penjahit>)

    fun getListDetailKategori(data: Penjahit, callback: ResponseCallback<List<ListDetailKategori>>)

    fun insertDataDetailKategoriPenjahit(data: DetailKategori, responseCallback: ResponseCallback<DetailKategori>)

    fun deleteDataDetailKategori(data: DetailKategori, responseCallback: ResponseCallback<DetailKategori>)
}
