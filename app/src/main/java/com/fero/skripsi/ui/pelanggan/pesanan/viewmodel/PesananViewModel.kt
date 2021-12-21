package com.fero.skripsi.ui.pelanggan.pesanan.viewmodel

import com.fero.skripsi.core.BaseViewModel
import com.fero.skripsi.data.Repository
import com.fero.skripsi.data.source.ResponseCallback
import com.fero.skripsi.model.DetailPesanan
import com.fero.skripsi.model.Pelanggan
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.model.Pesanan
import com.fero.skripsi.utils.SingleLiveEvent

class PesananViewModel(private val repository: Repository) : BaseViewModel() {

    var listPesanan = SingleLiveEvent<List<Pesanan>>()
    var dataPesanan = SingleLiveEvent<Pesanan>()
    var dataDetailPesanan = SingleLiveEvent<DetailPesanan>()
    var messageFailed = SingleLiveEvent<String>()
    var messageSuccess = SingleLiveEvent<String>()
    var showProgress = SingleLiveEvent<Boolean>()
    var onSuccessState = SingleLiveEvent<Boolean>()

    fun getDataPesananById(data: Pesanan){

        repository.getDataPesananById(data, object : ResponseCallback<Pesanan>{
            override fun onSuccess(data: Pesanan) {
                dataPesanan.postValue(data)
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

    fun getDataDetailPesananById(data: Pesanan){

        repository.getDataDetailPesananById(data, object : ResponseCallback<List<DetailPesanan>>{
            override fun onSuccess(data: List<DetailPesanan>) {
                dataDetailPesanan.postValue(data[0])
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
                eventIsEmpty.postValue(check)
            }

        })
    }

    fun getDataPesananByPelanggan(data: Pelanggan){

        repository.getDataPesananByPelanggan(data, object : ResponseCallback<List<Pesanan>>{
            override fun onSuccess(data: List<Pesanan>) {
                listPesanan.postValue(data)
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

    fun getDataPesananByPenjahit(data: Penjahit){

        repository.getDataPesananByPenjahit(data, object : ResponseCallback<List<Pesanan>>{
            override fun onSuccess(data: List<Pesanan>) {
                listPesanan.postValue(data)
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

    fun insertDataPesanan(data: Pesanan){

        repository.insertDataPesanan(data, object : ResponseCallback<Pesanan>{
            override fun onSuccess(data: Pesanan) {
                dataPesanan.postValue(data)
                messageSuccess.postValue("Data Berhasil Dikirim")
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
                eventIsEmpty.postValue(check)
            }

        })
    }

    fun updateDataPesanan(data: Pesanan){

        repository.updateDataPesanan(data, object : ResponseCallback<Pesanan>{
            override fun onSuccess(data: Pesanan) {
                dataPesanan.postValue(data)
                messageSuccess.postValue("Data Berhasil Diubah")
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
                eventIsEmpty.postValue(check)
            }

        })
    }

    fun deleteDataPesanan(data: Pesanan){

        repository.deleteDataPesanan(data, object : ResponseCallback<Pesanan>{
            override fun onSuccess(data: Pesanan) {
                dataPesanan.postValue(data)
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
                eventIsEmpty.postValue(check)
            }

        })

    }
}