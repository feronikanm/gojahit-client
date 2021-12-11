package com.fero.skripsi.data

import com.fero.skripsi.data.source.ResponseCallback
import com.fero.skripsi.data.source.remote.RemoteDataSource
import com.fero.skripsi.model.*

class Repository private constructor(private val remoteDataSource: RemoteDataSource) : DataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(remoteData: RemoteDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteData).apply { instance = this }
            }
    }

    override fun getDataPenjahitNilai(callback: ResponseCallback<List<DetailKategoriNilai>>) {
        remoteDataSource.getDataPenjahitNilai(callback)
    }

    override fun getDataPenjahit(callback: ResponseCallback<List<DetailKategoriNilai>>) {
        remoteDataSource.getDataPenjahit(callback)
    }

    override fun getDataKategori(callback: ResponseCallback<List<DetailKategoriNilai>>) {
        remoteDataSource.getDataKategori(callback)
    }

    override fun getDataPelangganById(
        data: Pelanggan,
        responseCallback: ResponseCallback<List<Pelanggan>>
    ) {
        remoteDataSource.getDataPelangganById(data, responseCallback)
    }

    override fun getDataPenjahitById(
        data: Penjahit,
        responseCallback: ResponseCallback<List<Penjahit>>) {
        remoteDataSource.getDataPenjahitById(data, responseCallback)
    }

    override fun registerPelanggan(
        data: Pelanggan,
        responseCallback: ResponseCallback<Pelanggan>
    ) {
        remoteDataSource.registerPelanggan(data, responseCallback)
    }

    override fun loginPelanggan(
        email: String, password: String,
        responseCallback: ResponseCallback<Pelanggan>
    ) {
        remoteDataSource.loginPelanggan(email, password, responseCallback)
    }

    override fun updatePelanggan(
        data: Pelanggan,
        responseCallback: ResponseCallback<Pelanggan>
    ) {
        remoteDataSource.updatePelanggan(data, responseCallback)
    }

    override fun registerPenjahit(
        data: Penjahit,
        responseCallback: ResponseCallback<Penjahit>
    ) {
        remoteDataSource.registerPenjahit(data, responseCallback)
    }

    override fun loginPenjahit(
        email: String, password: String,
        responseCallback: ResponseCallback<Penjahit>
    ) {
        remoteDataSource.loginPenjahit(email, password, responseCallback)
    }

    override fun updatePenjahit(data: Penjahit, responseCallback: ResponseCallback<Penjahit>) {
        remoteDataSource.updatePenjahit(data, responseCallback)
    }

    override fun getListDetailKategori(
        data: Penjahit,
        callback: ResponseCallback<List<DetailKategoriPenjahit>>
    ) {
        remoteDataSource.getListDetailKategori(data, callback)
    }

    override fun getListDetailKategoriInPelanggan(
        data: DetailKategoriNilai, callback: ResponseCallback<List<DetailKategoriNilai>>
    ) {
        remoteDataSource.getListDetailKategoriInPelanggan(data, callback)
    }

    override fun insertDataDetailKategoriPenjahit(
        data: DetailKategori,
        responseCallback: ResponseCallback<DetailKategori>
    ) {
        remoteDataSource.insertDataDetailKategoriPenjahit(data, responseCallback)
    }

    override fun deleteDataDetailKategori(
        data: DetailKategoriPenjahit,
        responseCallback: ResponseCallback<Int>
    ) {
        remoteDataSource.deleteDataDetailKategori(data, responseCallback)
    }

    override fun updateDataDetailKategori(
        data: DetailKategori,
        responseCallback: ResponseCallback<DetailKategori>
    ) {
        remoteDataSource.updateDataDetailKategori(data, responseCallback)
    }

    override fun getDataPenjahitByKategori(
        data: DetailKategoriNilai,
        callback: ResponseCallback<List<DetailKategoriNilai>>
    ) {
        remoteDataSource.getDataPenjahitByKategori(data, callback)
    }

    override fun getDataUkuran(callback: ResponseCallback<List<UkuranDetailKategori>>) {
        remoteDataSource.getDataUkuran(callback)
    }

    override fun getUkuranByDetailKategori(
        data: UkuranDetailKategori,
        callback: ResponseCallback<List<UkuranDetailKategori>>
    ) {
        remoteDataSource.getUkuranByDetailKategori(data, callback)
    }

    override fun insertDataUkuranDetailKategori(
        data: UkuranDetailKategori,
        responseCallback: ResponseCallback<UkuranDetailKategori>
    ) {
        remoteDataSource.insertDataUkuranDetailKategori(data, responseCallback)
    }

    override fun deleteDataUkuranDetailKategori(
        data: UkuranDetailKategori,
        responseCallback: ResponseCallback<ResponseDeleteSuccess>
    ) {
        remoteDataSource.deleteDataUkuranDetailKategori(data, responseCallback)
    }

    override fun insertDataRating(data: Rating, responseCallback: ResponseCallback<Rating>) {
        remoteDataSource.insertDataRating(data, responseCallback)
    }

    override fun getDataPesananById(data: Pesanan, responseCallback: ResponseCallback<Pesanan>) {
        remoteDataSource.getDataPesananById(data, responseCallback)
    }

    override fun getDataPesananByPelanggan(
        data: Pelanggan,
        callback: ResponseCallback<List<Pesanan>>
    ) {
        remoteDataSource.getDataPesananByPelanggan(data, callback)
    }

    override fun getDataPesananByPenjahit(
        data: Penjahit,
        callback: ResponseCallback<List<Pesanan>>
    ) {
        remoteDataSource.getDataPesananByPenjahit(data, callback)
    }

    override fun insertDataPesanan(data: Pesanan, responseCallback: ResponseCallback<Pesanan>) {
        remoteDataSource.insertDataPesanan(data, responseCallback)
    }

    override fun updateDataPesanan(data: Pesanan, responseCallback: ResponseCallback<Pesanan>) {
        remoteDataSource.updateDataPesanan(data, responseCallback)
    }

    override fun deleteDataPesanan(data: Pesanan, responseCallback: ResponseCallback<Pesanan>) {
        remoteDataSource.deleteDataPesanan(data, responseCallback)
    }

    override fun getDataUkuranByPesanan(
        data: Pesanan,
        callback: ResponseCallback<List<UkuranDetailPesanan>>
    ) {
        remoteDataSource.getDataUkuranByPesanan(data, callback)
    }

    override fun getDataUkuranPesananByDetailKategori(
        data: Pesanan,
        callback: ResponseCallback<List<UkuranDetailPesanan>>
    ) {
        remoteDataSource.getDataUkuranPesananByDetailKategori(data, callback)
    }

    override fun insertDataUkuranDetailPesanan(
        data: UkuranDetailPesanan,
        responseCallback: ResponseCallback<UkuranDetailPesanan>
    ) {
        remoteDataSource.insertDataUkuranDetailPesanan(data, responseCallback)
    }

    override fun updateDatakuranDetailPesanan(
        data: UkuranDetailPesanan,
        responseCallback: ResponseCallback<UkuranDetailPesanan>
    ) {
        remoteDataSource.updateDatakuranDetailPesanan(data, responseCallback)
    }

    override fun deleteDatakuranDetailPesanan(
        data: UkuranDetailPesanan,
        responseCallback: ResponseCallback<UkuranDetailPesanan>
    ) {
        remoteDataSource.deleteDatakuranDetailPesanan(data, responseCallback)
    }

}