package com.fero.skripsi.ui.pelanggan.auth.viewmodel

import androidx.lifecycle.ViewModel
import com.fero.skripsi.data.Repository
import com.fero.skripsi.data.source.ResponseCallback
import com.fero.skripsi.model.Pelanggan
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.utils.SingleLiveEvent

class AuthPelangganViewModel(private val repository: Repository) : ViewModel() {

    var dataPelanggan = SingleLiveEvent<Pelanggan>()
    var dataPelangganVM = SingleLiveEvent<Pelanggan>()
    var messageFailed = SingleLiveEvent<String>()
    var messageSuccess = SingleLiveEvent<String>()
    var showProgress = SingleLiveEvent<Boolean>()
    var onSuccessState = SingleLiveEvent<Boolean>()

    fun getDataPelangganById(data: Pelanggan){

        repository.getDataPelangganById(data, object : ResponseCallback<List<Pelanggan>>{
            override fun onSuccess(data: List<Pelanggan>) {
                dataPelangganVM.postValue(data[0])
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
            }

        })
    }

    fun registerPelanggan(data: Pelanggan) {

        repository.registerPelanggan(data, object : ResponseCallback<Pelanggan> {
            override fun onSuccess(data: Pelanggan) {
                dataPelanggan.postValue(data)
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

    fun loginPelanggan(email: String, password: String) {

        repository.loginPelanggan(email, password, object : ResponseCallback<Pelanggan> {
            override fun onSuccess(data: Pelanggan) {
                dataPelanggan.postValue(data)
                messageSuccess.postValue("Login Berhasil")
                onSuccessState.postValue(true)
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                messageFailed.postValue(errorMessage)
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

    fun updatePelanggan(data: Pelanggan) {

        repository.updatePelanggan(data, object : ResponseCallback<Pelanggan> {
            override fun onSuccess(data: Pelanggan) {
                dataPelanggan.postValue(data)
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