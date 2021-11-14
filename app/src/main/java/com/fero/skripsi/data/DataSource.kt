package com.fero.skripsi.data

import com.fero.skripsi.data.source.ResponseCallback
import com.fero.skripsi.model.*

interface DataSource {

    fun getDataPenjahit(callback: ResponseCallback<List<DetailKategoriNilai>>)

    fun getDataKategori(callback: ResponseCallback<List<DetailKategoriNilai>>)

    fun registerPelanggan(data: Pelanggan, responseCallback: ResponseCallback<Pelanggan>)

    fun loginPelanggan(email: String, password: String, responseCallback: ResponseCallback<Pelanggan>)

    fun updatePelanggan(data: Pelanggan, responseCallback: ResponseCallback<Pelanggan>)

    fun registerPenjahit(data: Penjahit, responseCallback: ResponseCallback<Penjahit>)

    fun loginPenjahit(email: String, password: String, responseCallback: ResponseCallback<Penjahit>)

    fun updatePenjahit(data: Penjahit, responseCallback: ResponseCallback<Penjahit>)

    fun getListDetailKategori(data: Penjahit, callback: ResponseCallback<List<ListDetailKategori>>)

    fun insertDataDetailKategoriPenjahit(data: DetailKategori, responseCallback: ResponseCallback<DetailKategori>)

    fun deleteDataDetailKategori(data: ListDetailKategori, responseCallback: ResponseCallback<ListDetailKategori>)

    fun updateDataDetailKategori(data: ListDetailKategori, responseCallback: ResponseCallback<ListDetailKategori>)

    fun getDataPenjahitByKategori(data: DetailKategoriNilai, callback: ResponseCallback<List<DetailKategoriNilai>>)

    fun getDataUkuran(data: UkuranDetailKategori, callback: ResponseCallback<List<UkuranDetailKategori>>)

    fun getUkuranByDetailKategori(data: UkuranDetailKategori, callback: ResponseCallback<List<UkuranDetailKategori>>)

}
