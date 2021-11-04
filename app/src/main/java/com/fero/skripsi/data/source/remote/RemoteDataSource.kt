package com.fero.skripsi.data.source.remote

import android.content.Context
import android.util.Log
import com.fero.skripsi.data.DataSource
import com.fero.skripsi.data.source.ResponseCallback
import com.fero.skripsi.model.*
import com.fero.skripsi.utils.EspressoIdlingResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(context: Context) : DataSource {

    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(context: Context): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(context).apply { instance = this }
            }
    }

    private val apiService = ApiConfig.getApiService(context)

    override fun getDataPenjahit(callback: ResponseCallback<List<Nilai>>) {
        EspressoIdlingResource.increment()
        apiService.getDataPenjahit().subscribeOn(Schedulers.io())
            .doOnSubscribe { callback.onShowProgress() }
            .doOnTerminate { callback.onHideProgress() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<List<Nilai>>() {
                override fun onSuccess(data: List<Nilai>) {
                    if (data.isNotEmpty()) {
                        callback.onSuccess(data)
                        callback.isEmptyData(false)
                    } else {
                        callback.isEmptyData(true)
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onFailed(code, errorMessage)
                    EspressoIdlingResource.decrement()
                }
            })
    }

    override fun getDataKategori(callback: ResponseCallback<List<Kategori>>) {
        EspressoIdlingResource.increment()
        apiService.getDataKategori().subscribeOn(Schedulers.io())
            .doOnSubscribe { callback.onShowProgress() }
            .doOnTerminate { callback.onHideProgress() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<List<Kategori>>() {
                override fun onSuccess(data: List<Kategori>) {
                    if (data.isNotEmpty()) {
                        callback.onSuccess(data)
                        callback.isEmptyData(false)
                    } else {
                        callback.isEmptyData(true)
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onFailed(code, errorMessage)
                    EspressoIdlingResource.decrement()
                }
            })
    }

    override fun registerPelanggan(data: Pelanggan, responseCallback: ResponseCallback<Pelanggan>) {
        EspressoIdlingResource.increment()

        responseCallback.onShowProgress()

        var checkEmail = 0

        apiService.getPelanggan().enqueue(object : Callback<List<Pelanggan>> {
            override fun onResponse(
                call: Call<List<Pelanggan>>,
                response: Response<List<Pelanggan>>
            ) {
                for (i in response.body()?.indices!!) {
                    if (data.email_pelanggan.equals(response.body()!!.get(i).email_pelanggan)) {
                        checkEmail++
                    }
                }

                if (checkEmail != 0) {
                    responseCallback.onFailed(500, "Email Sudah Terdaftar")
                } else {
                    apiService.insertDataPelanggan(
                        nama_pelanggan = data.nama_pelanggan!!,
                        email_pelanggan = data.email_pelanggan!!,
                        password_pelanggan = data.password_pelanggan!!,
                        telp_pelanggan = data.telp_pelanggan!!,
                        latitude_pelanggan = data.latitude_pelanggan!!,
                        longitude_pelanggan = data.longitude_pelanggan!!,
                        alamat_pelanggan = data.alamat_pelanggan!!,
                        jk_pelanggan = data.jk_pelanggan!!,
                        foto_pelanggan = data.foto_pelanggan!!,
                    ).enqueue(object : Callback<Success<Pelanggan>> {
                        override fun onResponse(
                            call: Call<Success<Pelanggan>>,
                            response: Response<Success<Pelanggan>>
                        ) {
                            responseCallback.onHideProgress()
                            response.body()?.data?.let { responseCallback.onSuccess(it) }
                            Log.d("Repsonse Body", response.body().toString())
                            EspressoIdlingResource.decrement()
                        }

                        override fun onFailure(call: Call<Success<Pelanggan>>, t: Throwable) {
                            responseCallback.onHideProgress()
                            EspressoIdlingResource.decrement()
                            responseCallback.onFailed(500, t.localizedMessage)
                        }

                    })
                }

            }

            override fun onFailure(call: Call<List<Pelanggan>>, t: Throwable) {
                EspressoIdlingResource.decrement()
                responseCallback.onFailed(500, "Gagal Mengecek Data Server")
            }

        })

    }

    override fun loginPelanggan(
        email: String,
        password: String,
        responseCallback: ResponseCallback<Pelanggan>
    ) {

        EspressoIdlingResource.increment()

        responseCallback.onShowProgress()

        apiService.getPelanggan().enqueue(object : Callback<List<Pelanggan>> {
            override fun onResponse(
                call: Call<List<Pelanggan>>,
                response: Response<List<Pelanggan>>
            ) {

                val data = mutableListOf<Pelanggan>()
                response.body()?.let { data.addAll(it) }

                var valid = true

                for (i in data.indices) {
                    if (email.equals(data[i].email_pelanggan) && password.equals(data[i].password_pelanggan)) {
                        responseCallback.onSuccess(data[i])
                        valid = true
                        break

                    } else {
                        valid = false
                    }
                }

                if (!valid) {
                    responseCallback.onFailed(500, "Data yang anda masukkan tidak valid")
                }
            }

            override fun onFailure(call: Call<List<Pelanggan>>, t: Throwable) {
                EspressoIdlingResource.decrement()
                responseCallback.onFailed(500, "Gagal Mengecek Data Server")
            }

        })
    }

    override fun registerPenjahit(data: Penjahit, responseCallback: ResponseCallback<Penjahit>) {

        EspressoIdlingResource.increment()

        responseCallback.onShowProgress()

        var checkEmail = 0

        apiService.getPenjahit().enqueue(object : Callback<List<Penjahit>> {
            override fun onResponse(
                call: Call<List<Penjahit>>,
                response: Response<List<Penjahit>>
            ) {
                for (i in response.body()?.indices!!) {
                    if (data.email_penjahit.equals(response.body()!!.get(i).email_penjahit)) {
                        checkEmail++
                    }
                }

                if (checkEmail != 0) {
                    responseCallback.onFailed(500, "Email Sudah Terdaftar")
                } else {
                    apiService.insertDataPenjahit(
                        nama_penjahit = data.nama_penjahit!!,
                        email_penjahit = data.email_penjahit!!,
                        password_penjahit = data.password_penjahit!!,
                        telp_penjahit = data.telp_penjahit!!,
                        latitude_penjahit = data.latitude_penjahit!!,
                        longitude_penjahit = data.longitude_penjahit!!,
                        alamat_penjahit = data.alamat_penjahit!!,
                        jangkauan_kategori_penjahit = data.jangkauan_kategori_penjahit!!,
                        foto_penjahit = data.foto_penjahit!!,
                        hari_buka = data.hari_buka.toString(),
                        jam_buka = data.jam_buka.toString(),
                        jam_tutup = data.jam_tutup.toString(),
                        keterangan_toko = data.keterangan_toko.toString(),
                        nama_toko = data.nama_toko!!,
                        spesifikasi_penjahit = data.spesifikasi_penjahit!!,

                        ).enqueue(object : Callback<Success<Penjahit>> {

                        override fun onResponse(
                            call: Call<Success<Penjahit>>,
                            response: Response<Success<Penjahit>>
                        ) {
                            responseCallback.onHideProgress()
                            response.body()?.data?.let { responseCallback.onSuccess(it) }
                            Log.d("Repsonse Body", response.body().toString())
                            EspressoIdlingResource.decrement()
                        }

                        override fun onFailure(call: Call<Success<Penjahit>>, t: Throwable) {
                            responseCallback.onHideProgress()
                            EspressoIdlingResource.decrement()
                            responseCallback.onFailed(500, t.localizedMessage)
                        }
                    })
                }
            }

            override fun onFailure(call: Call<List<Penjahit>>, t: Throwable) {
                EspressoIdlingResource.decrement()
                responseCallback.onFailed(500, "Gagal Mengecek Data Server")
            }

        })
    }

    override fun loginPenjahit(
        email: String,
        password: String,
        responseCallback: ResponseCallback<Penjahit>
    ) {
        EspressoIdlingResource.increment()

        responseCallback.onShowProgress()

        apiService.getPenjahit().enqueue(object : Callback<List<Penjahit>> {
            override fun onResponse(
                call: Call<List<Penjahit>>,
                response: Response<List<Penjahit>>
            ) {
                val data = mutableListOf<Penjahit>()
                response.body()?.let { data.addAll(it) }

                var valid = true

                for (i in data.indices) {
                    if (email.equals(data[i].email_penjahit) && password.equals(data[i].password_penjahit)) {
                        responseCallback.onSuccess(data[i])
                        valid = true
                        break

                    } else {
                        valid = false
                    }
                }

                if(!valid){
                    responseCallback.onFailed(500, "Data yang anda masukkan tidak valid")
                }
            }

            override fun onFailure(call: Call<List<Penjahit>>, t: Throwable) {
                EspressoIdlingResource.decrement()
                responseCallback.onFailed(500, "Gagal Mengecek Data Server")
            }

        })
    }

    override fun updatePelanggan(data: Pelanggan, responseCallback: ResponseCallback<Pelanggan>) {

        EspressoIdlingResource.increment()

        responseCallback.onShowProgress()

        apiService.updateDataPelanggan(
            data.id_pelanggan!!,
            nama_pelanggan = data.nama_pelanggan!!,
            email_pelanggan = data.email_pelanggan!!,
            password_pelanggan = data.password_pelanggan!!,
            telp_pelanggan = data.telp_pelanggan!!,
            latitude_pelanggan = data.latitude_pelanggan!!,
            longitude_pelanggan = data.longitude_pelanggan!!,
            alamat_pelanggan = data.alamat_pelanggan!!,
            jk_pelanggan = data.jk_pelanggan!!,
            foto_pelanggan = data.foto_pelanggan!!,
        ).enqueue(object : Callback<Success<Pelanggan>> {
            override fun onResponse(
                call: Call<Success<Pelanggan>>,
                response: Response<Success<Pelanggan>>
            ) {
                responseCallback.onHideProgress()
                response.body()?.data?.let { responseCallback.onSuccess(it) }
                Log.d("Repsonse Body", response.body().toString())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<Success<Pelanggan>>, t: Throwable) {
                responseCallback.onHideProgress()
                EspressoIdlingResource.decrement()
                responseCallback.onFailed(500, t.localizedMessage)
            }

        })
    }

    override fun updatePenjahit(data: Penjahit, responseCallback: ResponseCallback<Penjahit>) {

        EspressoIdlingResource.increment()

        responseCallback.onShowProgress()

        apiService.updateDataPenjahit(
            data.id_penjahit!!,
            nama_penjahit = data.nama_penjahit!!,
            email_penjahit = data.email_penjahit!!,
            password_penjahit = data.password_penjahit!!,
            telp_penjahit = data.telp_penjahit!!,
            latitude_penjahit = data.latitude_penjahit!!,
            longitude_penjahit = data.longitude_penjahit!!,
            alamat_penjahit = data.alamat_penjahit!!,
            jangkauan_kategori_penjahit = data.jangkauan_kategori_penjahit!!,
            foto_penjahit = data.foto_penjahit!!,
            hari_buka = data.hari_buka.toString(),
            jam_buka = data.jam_buka.toString(),
            jam_tutup = data.jam_tutup.toString(),
            keterangan_toko = data.keterangan_toko.toString(),
            nama_toko = data.nama_toko!!,
            spesifikasi_penjahit = data.spesifikasi_penjahit!!,
        ).enqueue(object : Callback<Success<Penjahit>> {
            override fun onResponse(
                call: Call<Success<Penjahit>>,
                response: Response<Success<Penjahit>>
            ) {
                responseCallback.onHideProgress()
                response.body()?.data?.let { responseCallback.onSuccess(it) }
                Log.d("Repsonse Body", response.body().toString())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<Success<Penjahit>>, t: Throwable) {
                responseCallback.onHideProgress()
                EspressoIdlingResource.decrement()
                responseCallback.onFailed(500, t.localizedMessage)
            }

        })
    }

}