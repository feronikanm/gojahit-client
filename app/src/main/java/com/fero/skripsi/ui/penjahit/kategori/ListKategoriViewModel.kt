package com.fero.skripsi.ui.penjahit.kategori

import androidx.lifecycle.ViewModel
import com.fero.skripsi.core.BaseViewModel
import com.fero.skripsi.data.Repository
import com.fero.skripsi.data.source.ResponseCallback
import com.fero.skripsi.model.DetailKategori
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.utils.SingleLiveEvent

class ListKategoriViewModel(private val repository: Repository) : BaseViewModel() {

    var listDetailKategori = SingleLiveEvent<List<DetailKategori>>()

    fun getListDetailKategori(data: Penjahit){

        repository.getListDetailKategori(data, object : ResponseCallback<List<DetailKategori>>{
            override fun onSuccess(data: List<DetailKategori>) {
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


}