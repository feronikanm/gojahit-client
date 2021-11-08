package com.fero.skripsi.ui.penjahit

import androidx.lifecycle.ViewModel
import com.fero.skripsi.data.Repository
import com.fero.skripsi.data.source.ResponseCallback
import com.fero.skripsi.model.Pelanggan
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.utils.SingleLiveEvent

class AuthPenjahitViewModel(private val repository: Repository) : ViewModel() {

    var dataPenjahit = SingleLiveEvent<Penjahit>()
    var messageFailed = SingleLiveEvent<String>()
    var messageSuccess = SingleLiveEvent<String>()
    var showProgress = SingleLiveEvent<Boolean>()
    var onSuccessState = SingleLiveEvent<Boolean>()

    fun registerPenjahit(data: Penjahit) {

        repository.registerPenjahit(data, object : ResponseCallback<Penjahit> {
            override fun onSuccess(data: Penjahit) {
                dataPenjahit.postValue(data)
                messageSuccess.postValue("Register Berhasil")
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

    fun loginPenjahit(email: String, password: String) {

        repository.loginPenjahit(email, password, object : ResponseCallback<Penjahit> {
            override fun onSuccess(data: Penjahit) {
                dataPenjahit.postValue(data)
                messageSuccess.postValue("Login Berhasil")
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

    fun updatePenjahit(data: Penjahit) {

        repository.updatePenjahit(data, object : ResponseCallback<Penjahit> {
            override fun onSuccess(data: Penjahit) {
                dataPenjahit.postValue(data)
                messageSuccess.postValue("Data Berhasil Diperbarui")
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