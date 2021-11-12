package com.fero.skripsi.data

import com.fero.skripsi.data.source.ResponseCallback
import com.fero.skripsi.data.source.remote.RemoteDataSource
import com.fero.skripsi.model.*

class Repository private constructor(private val remoteDataSource: RemoteDataSource) : DataSource{

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(remoteData: RemoteDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteData).apply { instance = this }
            }
    }

    override fun getDataPenjahit(callback: ResponseCallback<List<DetailKategoriNilai>>) {
        remoteDataSource.getDataPenjahit(callback)
    }

    override fun getDataKategori(callback: ResponseCallback<List<DetailKategoriNilai>>) {
        remoteDataSource.getDataKategori(callback)
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

    override fun getListDetailKategori(data: Penjahit, callback: ResponseCallback<List<ListDetailKategori>>) {
        remoteDataSource.getListDetailKategori(data, callback)
    }

    override fun insertDataDetailKategoriPenjahit(
        data: DetailKategori,
        responseCallback: ResponseCallback<DetailKategori>
    ) {
        remoteDataSource.insertDataDetailKategoriPenjahit(data, responseCallback)
    }

    override fun deleteDataDetailKategori(
        data: ListDetailKategori,
        responseCallback: ResponseCallback<ListDetailKategori>
    ) {
        remoteDataSource.deleteDataDetailKategori(data, responseCallback)
    }

    override fun updateDataDetailKategori(
        data: ListDetailKategori,
        responseCallback: ResponseCallback<ListDetailKategori>
    ) {
        remoteDataSource.updateDataDetailKategori(data, responseCallback)
    }

    override fun getDataPenjahitByKategori(
        data: DetailKategoriNilai,
        callback: ResponseCallback<List<DetailKategoriNilai>>
    ) {
        remoteDataSource.getDataPenjahitByKategori(data, callback)
    }


}