package com.fero.skripsi.data.source

interface ResponseCallback<T> {

    // If success fetching data from API
    fun onSuccess(data: T)

    // If failed fetching data from API
    fun onFailed(statusCode: Int, errorMessage: String? = "")

    // Show Progress View
    fun onShowProgress()

    // Hide Progress View
    fun onHideProgress()

    // isEmptyData
    fun isEmptyData(check: Boolean)

}