package com.fero.skripsi.ui.penjahit.kategori.viewmodel

import com.fero.skripsi.core.BaseViewModel
import com.fero.skripsi.data.Repository
import com.fero.skripsi.data.source.ResponseCallback
import com.fero.skripsi.model.DetailKategori
import com.fero.skripsi.model.DetailKategoriPenjahit
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.utils.SingleLiveEvent

class KategoriPenjahitViewModel(private val repository: Repository) : BaseViewModel() {

    var listDetailKategori = SingleLiveEvent<List<DetailKategoriPenjahit>>()
    var dataDetailKategori = SingleLiveEvent<DetailKategori>()
    var dataListDetailKategori = SingleLiveEvent<DetailKategoriPenjahit>()
    var messageFailed = SingleLiveEvent<String>()
    var messageSuccess = SingleLiveEvent<String>()
    var onSuccessState = SingleLiveEvent<Boolean>()
    var onSuccessDeleteState = SingleLiveEvent<Boolean>()
    var deleteItemPosition = SingleLiveEvent<Int>()

    fun getListDetailKategori(data: Penjahit){

        repository.getListDetailKategori(data, object : ResponseCallback<List<DetailKategoriPenjahit>>{
            override fun onSuccess(data: List<DetailKategoriPenjahit>) {
                listDetailKategori.postValue(data)
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

    fun insertDataDetailKategori(data: DetailKategori){
        repository.insertDataDetailKategoriPenjahit(data, object : ResponseCallback<DetailKategori>{
            override fun onSuccess(data: DetailKategori) {
                dataDetailKategori.postValue(data)
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

    fun deleteDataDetailKategori(data: DetailKategoriPenjahit, position: Int){

        repository.deleteDataDetailKategori(data, object : ResponseCallback<Int>{
            override fun onSuccess(data: Int) {
                onSuccessDeleteState.postValue(true)
                messageSuccess.postValue("Data Berhasil Dihapus")
                deleteItemPosition.postValue(position)
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

    fun updateDataDetailKategori(data: DetailKategoriPenjahit){
        repository.updateDataDetailKategori(data, object : ResponseCallback<DetailKategoriPenjahit>{
            override fun onSuccess(data: DetailKategoriPenjahit) {
                dataListDetailKategori.postValue(data)
                messageSuccess.postValue("Data Berhasil Diperbarui")
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