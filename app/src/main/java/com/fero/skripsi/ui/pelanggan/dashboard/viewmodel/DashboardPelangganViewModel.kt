package com.fero.skripsi.ui.pelanggan.dashboard.viewmodel

import com.fero.skripsi.core.BaseViewModel
import com.fero.skripsi.data.Repository
import com.fero.skripsi.data.source.ResponseCallback
import com.fero.skripsi.model.DetailKategoriNilai
import com.fero.skripsi.model.Kategori
import com.fero.skripsi.model.Nilai
import com.fero.skripsi.utils.SingleLiveEvent

class DashboardPelangganViewModel (private val repository: Repository) : BaseViewModel() {

    val listNilai = SingleLiveEvent<List<DetailKategoriNilai>>()
    val listKategori = SingleLiveEvent<List<DetailKategoriNilai>>()
    val listDataPenjahitByKategori = SingleLiveEvent<List<DetailKategoriNilai>>()

    fun getDataPenjahit(){
        repository.getDataPenjahit(object : ResponseCallback<List<DetailKategoriNilai>>{
            override fun onSuccess(data: List<DetailKategoriNilai>) {
                listNilai.postValue(data)
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                eventErrorMessage.postValue(errorMessage)
            }

            override fun onShowProgress() {
                eventShowProgress.postValue(true)
            }

            override fun onHideProgress() {
                eventShowProgress.postValue(false)
            }

            override fun isEmptyData(check: Boolean) {
                eventIsEmpty.postValue(check)
            }

        })
    }

    fun getDataKategori(){
        repository.getDataKategori(object : ResponseCallback<List<DetailKategoriNilai>>{
            override fun onSuccess(data: List<DetailKategoriNilai>) {
                listKategori.postValue(data)
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                eventErrorMessage.postValue(errorMessage)
            }

            override fun onShowProgress() {
                eventShowProgress.postValue(true)
            }

            override fun onHideProgress() {
                eventShowProgress.postValue(false)
            }

            override fun isEmptyData(check: Boolean) {
                eventIsEmpty.postValue(check)
            }

        })
    }

    fun getDataPenjahitByKategori(data: DetailKategoriNilai){
        repository.getDataPenjahitByKategori(data, object : ResponseCallback<List<DetailKategoriNilai>> {
            override fun onSuccess(data: List<DetailKategoriNilai>) {
                listDataPenjahitByKategori.postValue(data)
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                eventErrorMessage.postValue(errorMessage)
            }

            override fun onShowProgress() {
                eventShowProgress.postValue(true)
            }

            override fun onHideProgress() {
                eventShowProgress.postValue(false)
            }

            override fun isEmptyData(check: Boolean) {
                eventIsEmpty.postValue(check)
            }

        })
    }
}