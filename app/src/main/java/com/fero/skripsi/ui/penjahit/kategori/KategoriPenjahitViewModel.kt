package com.fero.skripsi.ui.penjahit.kategori

import com.fero.skripsi.core.BaseViewModel
import com.fero.skripsi.data.Repository
import com.fero.skripsi.data.source.ResponseCallback
import com.fero.skripsi.model.DetailKategori
import com.fero.skripsi.model.ListDetailKategori
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.utils.SingleLiveEvent

class KategoriPenjahitViewModel(private val repository: Repository) : BaseViewModel() {

    var listDetailKategori = SingleLiveEvent<List<ListDetailKategori>>()
    var dataDetailKategori = SingleLiveEvent<DetailKategori>()
    var messageFailed = SingleLiveEvent<String>()
    var messageSuccess = SingleLiveEvent<String>()
    var onSuccessState = SingleLiveEvent<Boolean>()

    fun getListDetailKategori(data: Penjahit){

        repository.getListDetailKategori(data, object : ResponseCallback<List<ListDetailKategori>>{
            override fun onSuccess(data: List<ListDetailKategori>) {
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

    fun deleteDataDetailKategori(data: DetailKategori){
        repository.deleteDataDetailKategori(data, object : ResponseCallback<DetailKategori>{
            override fun onSuccess(data: DetailKategori) {
                dataDetailKategori.postValue(data)
                messageSuccess.postValue("Data Berhasil Dihapus")
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