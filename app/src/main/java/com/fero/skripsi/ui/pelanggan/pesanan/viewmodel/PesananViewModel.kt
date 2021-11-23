package com.fero.skripsi.ui.pelanggan.pesanan.viewmodel

import com.fero.skripsi.core.BaseViewModel
import com.fero.skripsi.data.Repository
import com.fero.skripsi.model.Pesanan
import com.fero.skripsi.utils.SingleLiveEvent

class PesananViewModel(private val repository: Repository) : BaseViewModel() {

    var dataPesanan = SingleLiveEvent<Pesanan>()
    var messageFailed = SingleLiveEvent<String>()
    var messageSuccess = SingleLiveEvent<String>()
    var showProgress = SingleLiveEvent<Boolean>()
    var onSuccessState = SingleLiveEvent<Boolean>()

}