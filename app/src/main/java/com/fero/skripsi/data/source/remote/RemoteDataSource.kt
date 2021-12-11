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

    override fun getDataPenjahitNilai(callback: ResponseCallback<List<DetailKategoriNilai>>) {
        EspressoIdlingResource.increment()
        apiService.getDataPenjahitNilai().subscribeOn(Schedulers.io())
            .doOnSubscribe { callback.onShowProgress() }
            .doOnTerminate { callback.onHideProgress() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<List<DetailKategoriNilai>>() {
                override fun onSuccess(data: List<DetailKategoriNilai>) {
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

    override fun getDataPenjahit(callback: ResponseCallback<List<DetailKategoriNilai>>) {
        EspressoIdlingResource.increment()
        apiService.getDataPenjahit().subscribeOn(Schedulers.io())
            .doOnSubscribe { callback.onShowProgress() }
            .doOnTerminate { callback.onHideProgress() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<List<DetailKategoriNilai>>() {
                override fun onSuccess(data: List<DetailKategoriNilai>) {
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

    override fun getDataKategori(callback: ResponseCallback<List<DetailKategoriNilai>>) {
        EspressoIdlingResource.increment()
        apiService.getDataKategori().subscribeOn(Schedulers.io())
            .doOnSubscribe { callback.onShowProgress() }
            .doOnTerminate { callback.onHideProgress() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<List<DetailKategoriNilai>>() {
                override fun onSuccess(data: List<DetailKategoriNilai>) {
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

    override fun getDataPelangganById(
        data: Pelanggan,
        responseCallback: ResponseCallback<List<Pelanggan>>
    ) {
        EspressoIdlingResource.increment()
        apiService.getDataPelangganById(data.id_pelanggan!!).enqueue(object : Callback<List<Pelanggan>>{
            override fun onResponse(call: Call<List<Pelanggan>>, response: Response<List<Pelanggan>>) {
                responseCallback.onHideProgress()
                responseCallback.onSuccess(response.body()!!)
                Log.d("Repsonse : ", data.toString())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<List<Pelanggan>>, t: Throwable) {
                responseCallback.onHideProgress()
                responseCallback.onFailed(500)
                EspressoIdlingResource.decrement()
            }

        })

    }

    override fun getDataPenjahitById(
        data: Penjahit,
        responseCallback: ResponseCallback<List<Penjahit>>) {
        EspressoIdlingResource.increment()
        apiService.getDataPenjahitById(data.id_penjahit!!).enqueue(object : Callback<List<Penjahit>>{
            override fun onResponse(call: Call<List<Penjahit>>, response: Response<List<Penjahit>>) {
                responseCallback.onHideProgress()
                responseCallback.onSuccess(response.body()!!)
                Log.d("Repsonse : ", data.toString())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<List<Penjahit>>, t: Throwable) {
                responseCallback.onHideProgress()
                responseCallback.onFailed(500)
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

                if (!valid) {
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

    override fun getListDetailKategori(
        data: Penjahit,
        callback: ResponseCallback<List<DetailKategoriPenjahit>>
    ) {
        EspressoIdlingResource.increment()
        apiService.getDetailKategori(data.id_penjahit!!)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { callback.onShowProgress() }
            .doOnTerminate { callback.onHideProgress() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<List<DetailKategoriPenjahit>>() {
                override fun onSuccess(data: List<DetailKategoriPenjahit>) {
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

    override fun getListDetailKategoriInPelanggan(
        data: DetailKategoriNilai,
        callback: ResponseCallback<List<DetailKategoriNilai>>
    ) {
        EspressoIdlingResource.increment()
        apiService.getDetailKategoriInPelanggan(data.id_penjahit!!)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { callback.onShowProgress() }
            .doOnTerminate { callback.onHideProgress() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<List<DetailKategoriNilai>>() {
                override fun onSuccess(data: List<DetailKategoriNilai>) {
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

    override fun insertDataDetailKategoriPenjahit(
        data: DetailKategori,
        responseCallback: ResponseCallback<DetailKategori>
    ) {
        EspressoIdlingResource.increment()

        responseCallback.onShowProgress()

        apiService.insertDataDetailKategori(
            id_penjahit = data.id_penjahit!!,
            id_kategori = data.id_kategori!!,
            keterangan_kategori = data.keterangan_kategori!!,
            bahan_jahit = data.bahan_jahit!!,
            harga_bahan = data.harga_bahan,
            ongkos_penjahit = data.ongkos_penjahit,
            perkiraan_lama_waktu_pengerjaan = data.perkiraan_lama_waktu_pengerjaan!!,
        ).enqueue(object : Callback<Success<DetailKategori>> {
            override fun onResponse(
                call: Call<Success<DetailKategori>>,
                response: Response<Success<DetailKategori>>
            ) {
                responseCallback.onHideProgress()
                response.body()?.data?.let { responseCallback.onSuccess(it) }
                Log.d("Repsonse Body", response.body().toString())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<Success<DetailKategori>>, t: Throwable) {
                responseCallback.onHideProgress()
                EspressoIdlingResource.decrement()
                responseCallback.onFailed(500, t.localizedMessage)
            }

        })

    }

    override fun deleteDataDetailKategori(
        data: DetailKategoriPenjahit,
        responseCallback: ResponseCallback<Int>
    ) {
        EspressoIdlingResource.increment()

        responseCallback.onShowProgress()

        apiService.deleteDataDetailKategori(data.id_detail_kategori!!)
            .enqueue(object : Callback<Success<Int>> {
                override fun onResponse(
                    call: Call<Success<Int>>,
                    response: Response<Success<Int>>
                ) {
                    responseCallback.onHideProgress()
                    response.body()?.data?.let { responseCallback.onSuccess(it) }
                    Log.d("Repsonse Body", response.body().toString())
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<Success<Int>>, t: Throwable) {
                    responseCallback.onHideProgress()
                    EspressoIdlingResource.decrement()
                    responseCallback.onFailed(500, t.localizedMessage)
                }

            })
    }

    override fun updateDataDetailKategori(
        data: DetailKategori,
        responseCallback: ResponseCallback<DetailKategori>
    ) {
        EspressoIdlingResource.increment()

        responseCallback.onShowProgress()

        apiService.updateDataDetailKategori(
            data.id_detail_kategori!!,
            id_penjahit = data.id_penjahit!!,
            id_kategori = data.id_kategori!!,
            keterangan_kategori = data.keterangan_kategori!!,
            bahan_jahit = data.bahan_jahit!!,
            harga_bahan = data.harga_bahan,
            ongkos_penjahit = data.ongkos_penjahit,
            perkiraan_lama_waktu_pengerjaan = data.perkiraan_lama_waktu_pengerjaan!!,
        ).enqueue(object : Callback<Success<DetailKategori>> {
            override fun onResponse(
                call: Call<Success<DetailKategori>>,
                response: Response<Success<DetailKategori>>
            ) {
                responseCallback.onHideProgress()
                response.body()?.data?.let { responseCallback.onSuccess(it) }
                Log.d("Repsonse Body", response.body().toString())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<Success<DetailKategori>>, t: Throwable) {
                responseCallback.onHideProgress()
                EspressoIdlingResource.decrement()
                responseCallback.onFailed(500, t.localizedMessage)
            }

        })

    }

    override fun getDataPenjahitByKategori(
        data: DetailKategoriNilai,
        callback: ResponseCallback<List<DetailKategoriNilai>>
    ) {
        EspressoIdlingResource.increment()
        apiService.getDataPenjahitByKategori(data.id_kategori!!)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { callback.onShowProgress() }
            .doOnTerminate { callback.onHideProgress() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<List<DetailKategoriNilai>>() {
                override fun onSuccess(data: List<DetailKategoriNilai>) {
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

    override fun getDataUkuran(callback: ResponseCallback<List<UkuranDetailKategori>>) {
        EspressoIdlingResource.increment()
        apiService.getDataUkuran()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { callback.onShowProgress() }
            .doOnTerminate { callback.onHideProgress() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<List<UkuranDetailKategori>>() {
                override fun onSuccess(data: List<UkuranDetailKategori>) {
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

    override fun getUkuranByDetailKategori(
        data: UkuranDetailKategori,
        callback: ResponseCallback<List<UkuranDetailKategori>>
    ) {
        EspressoIdlingResource.increment()
        apiService.getUkuranByDetailKategori(data.id_detail_kategori!!)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { callback.onShowProgress() }
            .doOnTerminate { callback.onHideProgress() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<List<UkuranDetailKategori>>() {
                override fun onSuccess(data: List<UkuranDetailKategori>) {
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

    override fun insertDataUkuranDetailKategori(
        data: UkuranDetailKategori,
        responseCallback: ResponseCallback<UkuranDetailKategori>
    ) {
        EspressoIdlingResource.increment()

        responseCallback.onShowProgress()

        apiService.insertDataUkuranDetailKategori(
            id_detail_kategori = data.id_detail_kategori!!,
            id_ukuran = data.id_ukuran!!
        ).enqueue(object : Callback<Success<UkuranDetailKategori>> {
            override fun onResponse(
                call: Call<Success<UkuranDetailKategori>>,
                response: Response<Success<UkuranDetailKategori>>
            ) {
                responseCallback.onHideProgress()
                response.body()?.data?.let { responseCallback.onSuccess(it) }
                Log.d("Repsonse Body", response.body().toString())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<Success<UkuranDetailKategori>>, t: Throwable) {
                responseCallback.onHideProgress()
                EspressoIdlingResource.decrement()
                responseCallback.onFailed(500, t.localizedMessage)
            }

        })
    }

    override fun deleteDataUkuranDetailKategori(
        data: UkuranDetailKategori,
        responseCallback: ResponseCallback<ResponseDeleteSuccess>
    ) {
        EspressoIdlingResource.increment()

        responseCallback.onShowProgress()

        apiService.deleteDataUkuranDetailKategori(data.id_ukuran_detail_kategori!!)
            .enqueue(object : Callback<ResponseDeleteSuccess> {
                override fun onResponse(
                    call: Call<ResponseDeleteSuccess>,
                    response: Response<ResponseDeleteSuccess>
                ) {
                    responseCallback.onHideProgress()
                    response.body()?.let { responseCallback.onSuccess(it) }
                    EspressoIdlingResource.decrement()
                    Log.d("Repsonse Body", response.body().toString())
                }

                override fun onFailure(call: Call<ResponseDeleteSuccess>, t: Throwable) {
                    responseCallback.onHideProgress()
                    EspressoIdlingResource.decrement()
                    responseCallback.onFailed(500, t.localizedMessage)
                }
            })
    }

    override fun insertDataRating(data: Rating, responseCallback: ResponseCallback<Rating>) {
        EspressoIdlingResource.increment()

        responseCallback.onShowProgress()

        apiService.insertDataRating(
            id_penjahit = data.id_penjahit,
            kriteria_1 = data.kriteria_1,
            kriteria_2 = data.kriteria_2,
            kriteria_3 = data.kriteria_3,
            kriteria_4 = data.kriteria_4
        ).enqueue(object : Callback<Success<Rating>> {
            override fun onResponse(
                call: Call<Success<Rating>>,
                response: Response<Success<Rating>>
            ) {
                responseCallback.onHideProgress()
                response.body()?.data?.let { responseCallback.onSuccess(it) }
                Log.d("Repsonse Body", response.body().toString())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<Success<Rating>>, t: Throwable) {
                responseCallback.onHideProgress()
                EspressoIdlingResource.decrement()
                responseCallback.onFailed(500, t.localizedMessage)
            }

        })

    }

//    override fun getDataPesananById(data: Pesanan, responseCallback: ResponseCallback<Pesanan>) {
//        EspressoIdlingResource.increment()
//        apiService.getDataPesananById(data.id_pesanan!!)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : ApiCallback<Pesanan>(){
//                override fun onSuccess(data: Pesanan) {
//                    responseCallback.onSuccess(data)
//                    EspressoIdlingResource.decrement()
//                }
//                override fun onFailure(code: Int, errorMessage: String) {
//                    responseCallback.onFailed(code, errorMessage)
//                    EspressoIdlingResource.decrement()
//                }
//
//            })
//
//    }

    override fun getDataPesananById(data: Pesanan, responseCallback: ResponseCallback<Pesanan>) {
        EspressoIdlingResource.increment()
        apiService.getDataPesananById(data.id_pesanan!!).enqueue(object : Callback<Pesanan> {
            override fun onFailure(call: Call<Pesanan>, t: Throwable) {
                responseCallback.onHideProgress()
                responseCallback.onSuccess(data)
                Log.d("Repsonse : ", data.toString())
                EspressoIdlingResource.decrement()
            }

            override fun onResponse(call: Call<Pesanan>, response: Response<Pesanan>) {
                responseCallback.onHideProgress()
                responseCallback.onFailed(500)
                EspressoIdlingResource.decrement()
            }
        })
    }

    override fun getDataPesananByPelanggan(
        data: Pelanggan,
        callback: ResponseCallback<List<Pesanan>>
    ) {
        EspressoIdlingResource.increment()
        apiService.getDataPesananByPelanggan(data.id_pelanggan!!)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { callback.onShowProgress() }
            .doOnTerminate { callback.onHideProgress() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<List<Pesanan>>() {
                override fun onSuccess(data: List<Pesanan>) {
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

    override fun getDataPesananByPenjahit(
        data: Penjahit,
        callback: ResponseCallback<List<Pesanan>>
    ) {
        EspressoIdlingResource.increment()
        apiService.getDataPesananByPenjahit(data.id_penjahit!!)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { callback.onShowProgress() }
            .doOnTerminate { callback.onHideProgress() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<List<Pesanan>>() {
                override fun onSuccess(data: List<Pesanan>) {
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

    override fun insertDataPesanan(data: Pesanan, responseCallback: ResponseCallback<Pesanan>) {
        EspressoIdlingResource.increment()

        responseCallback.onShowProgress()

        apiService.insertDataPesanan(
            id_pelanggan = data.id_pelanggan,
            id_penjahit = data.id_penjahit,
            id_detail_kategori = data.id_detail_kategori,
            tanggal_pesanan = data.tanggal_pesanan,
            tanggal_pesanan_selesai = data.tanggal_pesanan_selesai,
            lama_waktu_pengerjaan = data.lama_waktu_pengerjaan,
            catatan_pesanan = data.catatan_pesanan,
            desain_jahitan = data.desain_jahitan,
            bahan_jahit = data.bahan_jahit,
            asal_bahan = data.asal_bahan,
            panjang_bahan = data.panjang_bahan,
            lebar_bahan = data.lebar_bahan,
            status_bahan = data.status_bahan,
            harga_bahan = data.harga_bahan,
            ongkos_penjahit = data.ongkos_penjahit,
            jumlah_jahitan = data.jumlah_jahitan,
            biaya_jahitan = data.biaya_jahitan,
            total_biaya = data.total_biaya,
            status_pesanan = data.status_pesanan,
        ).enqueue(object : Callback<Success<Pesanan>> {
            override fun onResponse(
                call: Call<Success<Pesanan>>,
                response: Response<Success<Pesanan>>
            ) {
                responseCallback.onHideProgress()
                response.body()?.data?.let { responseCallback.onSuccess(it) }
                Log.d("Repsonse Body", response.body().toString())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<Success<Pesanan>>, t: Throwable) {
                responseCallback.onHideProgress()
                EspressoIdlingResource.decrement()
                responseCallback.onFailed(500, t.localizedMessage)
            }

        })
    }

    override fun updateDataPesanan(data: Pesanan, responseCallback: ResponseCallback<Pesanan>) {
        EspressoIdlingResource.increment()

        responseCallback.onShowProgress()

        apiService.updateDataPesanan(
            data.id_pesanan,
            id_pelanggan = data.id_pelanggan,
            id_penjahit = data.id_penjahit,
            id_detail_kategori = data.id_detail_kategori,
            tanggal_pesanan = data.tanggal_pesanan,
            tanggal_pesanan_selesai = data.tanggal_pesanan_selesai,
            lama_waktu_pengerjaan = data.lama_waktu_pengerjaan,
            catatan_pesanan = data.catatan_pesanan,
            desain_jahitan = data.desain_jahitan,
            bahan_jahit = data.bahan_jahit,
            asal_bahan = data.asal_bahan,
            panjang_bahan = data.panjang_bahan,
            lebar_bahan = data.lebar_bahan,
            status_bahan = data.status_bahan,
            harga_bahan = data.harga_bahan,
            ongkos_penjahit = data.ongkos_penjahit,
            jumlah_jahitan = data.jumlah_jahitan,
            biaya_jahitan = data.biaya_jahitan,
            total_biaya = data.total_biaya,
            status_pesanan = data.status_pesanan,
        ).enqueue(object : Callback<Success<Pesanan>> {
            override fun onResponse(
                call: Call<Success<Pesanan>>,
                response: Response<Success<Pesanan>>
            ) {
                responseCallback.onHideProgress()
                response.body()?.data?.let { responseCallback.onSuccess(it) }
                Log.d("Repsonse Body", response.body().toString())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<Success<Pesanan>>, t: Throwable) {
                responseCallback.onHideProgress()
                EspressoIdlingResource.decrement()
                responseCallback.onFailed(500, t.localizedMessage)
            }

        })
    }

    override fun deleteDataPesanan(data: Pesanan, responseCallback: ResponseCallback<Pesanan>) {
        EspressoIdlingResource.increment()

        responseCallback.onShowProgress()

        apiService.deleteDataPesanan(data.id_pesanan!!)
            .enqueue(object : Callback<Success<Pesanan>> {
                override fun onResponse(
                    call: Call<Success<Pesanan>>,
                    response: Response<Success<Pesanan>>
                ) {
                    responseCallback.onHideProgress()
                    response.body()?.data?.let { responseCallback.onSuccess(it) }
                    Log.d("Repsonse Body", response.body().toString())
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<Success<Pesanan>>, t: Throwable) {
                    responseCallback.onHideProgress()
                    EspressoIdlingResource.decrement()
                    responseCallback.onFailed(500, t.localizedMessage)
                }

            })
    }

    override fun getDataUkuranByPesanan(
        data: Pesanan,
        callback: ResponseCallback<List<UkuranDetailPesanan>>
    ) {
        EspressoIdlingResource.increment()

        apiService.getDataUkuranByPesanan(data.id_pesanan!!)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { callback.onShowProgress() }
            .doOnTerminate { callback.onHideProgress() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<List<UkuranDetailPesanan>>() {
                override fun onSuccess(data: List<UkuranDetailPesanan>) {
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

    override fun getDataUkuranPesananByDetailKategori(
        data: Pesanan,
        callback: ResponseCallback<List<UkuranDetailPesanan>>
    ) {
        EspressoIdlingResource.increment()
        apiService.getDataUkuranPesananByDetailKategori(data.id_detail_kategori!!)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { callback.onShowProgress() }
            .doOnTerminate { callback.onHideProgress() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<List<UkuranDetailPesanan>>() {
                override fun onSuccess(data: List<UkuranDetailPesanan>) {
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

    override fun insertDataUkuranDetailPesanan(
        data: UkuranDetailPesanan,
        responseCallback: ResponseCallback<UkuranDetailPesanan>
    ) {
        EspressoIdlingResource.increment()

        responseCallback.onShowProgress()

        apiService.insertDataUkuranDetailPesanan(
            id_pesanan = data.id_pesanan,
            id_ukuran = data.id_ukuran,
            ukuran_pesanan = data.ukuran_pesanan,
        ).enqueue(object : Callback<Success<UkuranDetailPesanan>> {
            override fun onResponse(
                call: Call<Success<UkuranDetailPesanan>>,
                response: Response<Success<UkuranDetailPesanan>>
            ) {
                responseCallback.onHideProgress()
                response.body()?.data?.let { responseCallback.onSuccess(it) }
                Log.d("Repsonse Body", response.body().toString())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<Success<UkuranDetailPesanan>>, t: Throwable) {
                responseCallback.onHideProgress()
                EspressoIdlingResource.decrement()
                responseCallback.onFailed(500, t.localizedMessage)
            }

        })
    }

    override fun updateDatakuranDetailPesanan(
        data: UkuranDetailPesanan,
        responseCallback: ResponseCallback<UkuranDetailPesanan>
    ) {
        EspressoIdlingResource.increment()

        responseCallback.onShowProgress()

        apiService.updateDataUkuranDetailPesanan(
            data.id_ukuran_detail_pesanan,
            id_pesanan = data.id_pesanan,
            id_ukuran = data.id_ukuran,
            ukuran_pesanan = data.ukuran_pesanan
        ).enqueue(object : Callback<Success<UkuranDetailPesanan>> {
            override fun onResponse(
                call: Call<Success<UkuranDetailPesanan>>,
                response: Response<Success<UkuranDetailPesanan>>
            ) {
                responseCallback.onHideProgress()
                response.body()?.data?.let { responseCallback.onSuccess(it) }
                Log.d("Repsonse Body", response.body().toString())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<Success<UkuranDetailPesanan>>, t: Throwable) {
                responseCallback.onHideProgress()
                EspressoIdlingResource.decrement()
                responseCallback.onFailed(500, t.localizedMessage)
            }

        })

    }

    override fun deleteDatakuranDetailPesanan(
        data: UkuranDetailPesanan,
        responseCallback: ResponseCallback<UkuranDetailPesanan>
    ) {
        EspressoIdlingResource.increment()

        responseCallback.onShowProgress()

        apiService.deleteDataUkuranDetailPesanan(data.id_ukuran_detail_pesanan!!)
            .enqueue(object : Callback<Success<UkuranDetailPesanan>> {
                override fun onResponse(
                    call: Call<Success<UkuranDetailPesanan>>,
                    response: Response<Success<UkuranDetailPesanan>>
                ) {
                    responseCallback.onHideProgress()
                    response.body()?.data?.let { responseCallback.onSuccess(it) }
                    Log.d("Repsonse Body", response.body().toString())
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<Success<UkuranDetailPesanan>>, t: Throwable) {
                    responseCallback.onHideProgress()
                    EspressoIdlingResource.decrement()
                    responseCallback.onFailed(500, t.localizedMessage)
                }

            })
    }

}