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

    fun getListDetailKategori(data: Penjahit, callback: ResponseCallback<List<DetailKategoriPenjahit>>)

    fun getListDetailKategoriInPelanggan(data: DetailKategoriNilai, callback: ResponseCallback<List<DetailKategoriNilai>>)

    fun insertDataDetailKategoriPenjahit(data: DetailKategori, responseCallback: ResponseCallback<DetailKategori>)

    fun deleteDataDetailKategori(data: DetailKategoriPenjahit, responseCallback: ResponseCallback<DetailKategoriPenjahit>)

    fun updateDataDetailKategori(data: DetailKategoriPenjahit, responseCallback: ResponseCallback<DetailKategoriPenjahit>)

    fun getDataPenjahitByKategori(data: DetailKategoriNilai, callback: ResponseCallback<List<DetailKategoriNilai>>)

    fun getDataUkuran(callback: ResponseCallback<List<UkuranDetailKategori>>)

    fun getUkuranByDetailKategori(data: UkuranDetailKategori, callback: ResponseCallback<List<UkuranDetailKategori>>)

    fun insertDataUkuranDetailKategori(data: UkuranDetailKategori, responseCallback: ResponseCallback<UkuranDetailKategori>)

    fun deleteDataUkuranDetailKategori(data: UkuranDetailKategori, responseCallback: ResponseCallback<UkuranDetailKategori>)

    fun insertDataRating(data: Rating, responseCallback: ResponseCallback<Rating>)

    fun getDataPesananById(data: Pesanan, responseCallback: ResponseCallback<Pesanan>)

    fun getDataPesananByPelanggan(data: Pesanan, callback: ResponseCallback<List<Pesanan>>)

    fun getDataPesananByPenjahit(data: Pesanan, callback: ResponseCallback<List<Pesanan>>)

    fun insertDataPesanan(data: Pesanan, responseCallback: ResponseCallback<Pesanan>)

    fun updateDataPesanan(data: Pesanan, responseCallback: ResponseCallback<Pesanan>)

    fun deleteDataPesanan(data: Pesanan, responseCallback: ResponseCallback<Pesanan>)

}
