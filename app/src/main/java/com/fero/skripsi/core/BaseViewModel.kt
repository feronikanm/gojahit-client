package com.fero.skripsi.core

import androidx.lifecycle.ViewModel
import com.fero.skripsi.utils.SingleLiveEvent

open class BaseViewModel : ViewModel() {
    var eventShowProgress = SingleLiveEvent<Boolean>()
    var eventIsEmpty = SingleLiveEvent<Boolean>()
    var eventErrorMessage = SingleLiveEvent<String>()
}