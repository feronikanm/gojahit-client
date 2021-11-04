package com.fero.skripsi.data

import com.fero.skripsi.data.source.ResponseCallback
import com.fero.skripsi.data.source.remote.RemoteDataSource
import com.fero.skripsi.model.Kategori
import com.fero.skripsi.model.Nilai
import com.fero.skripsi.model.Pelanggan
import com.fero.skripsi.model.Penjahit

class Repository private constructor(private val remoteDataSource: RemoteDataSource) : DataSource{

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(remoteData: RemoteDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteData).apply { instance = this }
            }
    }

    override fun getDataPenjahit(callback: ResponseCallback<List<Nilai>>) {
        remoteDataSource.getDataPenjahit(callback)
    }

    override fun getDataKategori(callback: ResponseCallback<List<Kategori>>) {
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


}