package com.fero.skripsi.ui.penjahit.ukuran.viewmodel

import com.fero.skripsi.core.BaseViewModel
import com.fero.skripsi.data.Repository
import com.fero.skripsi.data.source.ResponseCallback
import com.fero.skripsi.model.UkuranDetailKategori
import com.fero.skripsi.utils.SingleLiveEvent

class UkuranViewModel(private val repository: Repository) : BaseViewModel() {

    var listUkuran = SingleLiveEvent<List<UkuranDetailKategori>>()
    var vmDataUkuran = SingleLiveEvent<UkuranDetailKategori>()
    var messageFailed = SingleLiveEvent<String>()
    var messageSuccess = SingleLiveEvent<String>()
    var onSuccessGetState = SingleLiveEvent<Boolean>()
    var onSuccessDeleteState = SingleLiveEvent<Boolean>()
    var onSuccessInsertState = SingleLiveEvent<Boolean>()


    fun getDataUkuran(){

        repository.getDataUkuran(object : ResponseCallback<List<UkuranDetailKategori>>{
            override fun onSuccess(data: List<UkuranDetailKategori>) {
                listUkuran.postValue(data)
                onSuccessGetState.postValue(true)
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                eventErrorMessage.postValue(errorMessage)
                onSuccessGetState.postValue(false)
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

    fun getUkuranByDetailKategori(data: UkuranDetailKategori){

        repository.getUkuranByDetailKategori(data, object : ResponseCallback<List<UkuranDetailKategori>>{
            override fun onSuccess(data: List<UkuranDetailKategori>) {
                listUkuran.postValue(data)
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

    fun insertDataUkuranDetailKategori(data: UkuranDetailKategori){

        repository.insertDataUkuranDetailKategori(data, object : ResponseCallback<UkuranDetailKategori>{
            override fun onSuccess(data: UkuranDetailKategori) {
                vmDataUkuran.postValue(data)
                messageSuccess.postValue("Data Berhasil Ditambahkan")
                onSuccessInsertState.postValue(true)
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                messageFailed.postValue(errorMessage)
                onSuccessInsertState.postValue(false)
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

    fun deleteDataUkuranDetailKategori(data: UkuranDetailKategori){

        repository.deleteDataUkuranDetailKategori(data, object : ResponseCallback<UkuranDetailKategori>{
            override fun onSuccess(data: UkuranDetailKategori) {
                vmDataUkuran.postValue(data)
                messageSuccess.postValue("Data Berhasil Dihapus\nKembali untuk melihat data yang telah dihapus")
                onSuccessDeleteState.postValue(true)
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                messageFailed.postValue(errorMessage)
                onSuccessDeleteState.postValue(false)
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