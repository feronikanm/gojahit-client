package com.fero.skripsi.ui.penjahit.ukuran.viewmodel

import com.fero.skripsi.core.BaseViewModel
import com.fero.skripsi.data.Repository
import com.fero.skripsi.data.source.ResponseCallback
import com.fero.skripsi.model.UkuranDetailKategori
import com.fero.skripsi.utils.SingleLiveEvent

class UkuranViewModel(private val repository: Repository) : BaseViewModel() {

    var listUkuran = SingleLiveEvent<List<UkuranDetailKategori>>()
    var dataUkuran = SingleLiveEvent<UkuranDetailKategori>()
    var messageFailed = SingleLiveEvent<String>()
    var messageSuccess = SingleLiveEvent<String>()
    var onSuccessState = SingleLiveEvent<Boolean>()


    fun getDataUkuran(){

        repository.getDataUkuran(object : ResponseCallback<List<UkuranDetailKategori>>{
            override fun onSuccess(data: List<UkuranDetailKategori>) {
                listUkuran.postValue(data)
                onSuccessState.postValue(true)
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                eventErrorMessage.postValue(errorMessage)
                onSuccessState.postValue(false)
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
                dataUkuran.postValue(data)
                messageSuccess.postValue("Data Berhasil Ditambahkan")
                onSuccessState.postValue(true)
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                messageFailed.postValue(errorMessage)
                onSuccessState.postValue(false)
            }

            override fun onShowProgress() {
                eventShowProgress.postValue(true)
            }

            override fun onHideProgress() {
                eventShowProgress.postValue(false)
            }

            override fun isEmptyData(check: Boolean) {
                TODO("Not yet implemented")
            }

        })
    }

    fun deleteDataUkuranDetailKategori(data: UkuranDetailKategori){

        repository.deleteDataUkuranDetailKategori(data, object : ResponseCallback<UkuranDetailKategori>{
            override fun onSuccess(data: UkuranDetailKategori) {
                dataUkuran.postValue(data)
                messageSuccess.postValue("Data Berhasil Dihapus\nKembali untuk melihat data yang telah dihapus")
                onSuccessState.postValue(true)
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                messageFailed.postValue(errorMessage)
                onSuccessState.postValue(false)
            }

            override fun onShowProgress() {
                eventShowProgress.postValue(true)
            }

            override fun onHideProgress() {
                eventShowProgress.postValue(false)
            }

            override fun isEmptyData(check: Boolean) {
                TODO("Not yet implemented")
            }

        })
    }


}