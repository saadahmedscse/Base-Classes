package com.saadahmedsoft.base.utils

import androidx.lifecycle.MutableLiveData

fun<T> getMutableLiveData() : MutableLiveData<DataState<T>> {
    return MutableLiveData<DataState<T>>()
}