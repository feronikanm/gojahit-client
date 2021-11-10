package com.fero.skripsi.ui.penjahit.dashboard

import androidx.lifecycle.MutableLiveData
import com.fero.skripsi.core.BaseViewModel
import com.fero.skripsi.data.Repository
import com.fero.skripsi.data.source.ResponseCallback
import com.fero.skripsi.model.Kategori
import com.fero.skripsi.model.Nilai
import com.fero.skripsi.utils.SingleLiveEvent

class DashboardPenjahitViewModel (private val repository: Repository) : BaseViewModel() {

    var listNilai = SingleLiveEvent<List<Nilai>>()
    var listKategori = SingleLiveEvent<List<Kategori>>()

    fun getDataPenjahit() {
        repository.getDataPenjahit(object : ResponseCallback<List<Nilai>>{
            override fun onSuccess(data: List<Nilai>) {
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
        repository.getDataKategori(object : ResponseCallback<List<Kategori>>{
            override fun onSuccess(data: List<Kategori>) {
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

}