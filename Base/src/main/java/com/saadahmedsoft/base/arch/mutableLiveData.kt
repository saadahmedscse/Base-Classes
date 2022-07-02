package com.saadahmedsoft.base.arch

import androidx.lifecycle.MutableLiveData
import com.saadahmedsoft.base.utils.DataState

fun<T> getMutableLiveData() : MutableLiveData<DataState<T>> {
    return MutableLiveData<DataState<T>>()
}