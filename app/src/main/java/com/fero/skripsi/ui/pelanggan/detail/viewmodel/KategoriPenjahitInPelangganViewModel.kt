package com.fero.skripsi.ui.pelanggan.detail.viewmodel

import com.fero.skripsi.core.BaseViewModel
import com.fero.skripsi.data.Repository
import com.fero.skripsi.data.source.ResponseCallback
import com.fero.skripsi.model.DetailKategori
import com.fero.skripsi.model.DetailKategoriNilai
import com.fero.skripsi.model.DetailKategoriPenjahit
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.utils.SingleLiveEvent

class KategoriPenjahitInPelangganViewModel(private val repository: Repository) : BaseViewModel() {

    var listDetailKategori = SingleLiveEvent<List<DetailKategoriNilai>>()
    var messageFailed = SingleLiveEvent<String>()
    var messageSuccess = SingleLiveEvent<String>()
    var onSuccessState = SingleLiveEvent<Boolean>()

    fun getListDetailKategori(data: DetailKategoriNilai){

        repository.getListDetailKategoriInPelanggan(data, object : ResponseCallback<List<DetailKategoriNilai>>{
            override fun onSuccess(data: List<DetailKategoriNilai>) {
                listDetailKategori.postValue(data)
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
}