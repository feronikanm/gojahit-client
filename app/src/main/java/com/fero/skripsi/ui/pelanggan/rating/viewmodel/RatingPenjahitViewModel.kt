package com.fero.skripsi.ui.pelanggan.rating.viewmodel

import androidx.lifecycle.ViewModel
import com.fero.skripsi.data.Repository
import com.fero.skripsi.data.source.ResponseCallback
import com.fero.skripsi.model.Rating
import com.fero.skripsi.utils.SingleLiveEvent

class RatingPenjahitViewModel(private val repository: Repository) : ViewModel() {

    var dataRating = SingleLiveEvent<Rating>()
    var messageFailed = SingleLiveEvent<String>()
    var messageSuccess = SingleLiveEvent<String>()
    var showProgress = SingleLiveEvent<Boolean>()
    var onSuccessState = SingleLiveEvent<Boolean>()

    fun insertDataRating(data: Rating){

        repository.insertDataRating(data, object : ResponseCallback<Rating>{
            override fun onSuccess(data: Rating) {
                dataRating.postValue(data)
                messageSuccess.postValue("Terimakasih, Data Penilaian telah dikirim")
                onSuccessState.postValue(true)
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                messageFailed.postValue(errorMessage)
                onSuccessState.postValue(false)
            }

            override fun onShowProgress() {
                showProgress.postValue(true)
            }

            override fun onHideProgress() {
                showProgress.postValue(false)
            }

            override fun isEmptyData(check: Boolean) {
                TODO("Not yet implemented")
            }

        })
    }

}