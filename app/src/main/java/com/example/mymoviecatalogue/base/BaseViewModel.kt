package com.example.mymoviecatalogue.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.utils.connectivitynetwork.ConnectivityObserver
import kotlinx.coroutines.launch

abstract class BaseViewModel(connectivityObserver: ConnectivityObserver) : ViewModel() {
    protected val _isLoading = MutableLiveData<Boolean>()
    protected val _error = MutableLiveData<String>()
    private val _networkStatus = MutableLiveData<String>()
    val isLoading: LiveData<Boolean> = _isLoading
    val error: LiveData<String> = _error
    val networkStatus: LiveData<String> = _networkStatus

    init {
        viewModelScope.launch {
            connectivityObserver.observe().collect {
                _networkStatus.value = it.name
            }
        }
    }

}