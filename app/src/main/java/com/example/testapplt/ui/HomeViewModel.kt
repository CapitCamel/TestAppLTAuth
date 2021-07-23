package com.example.testapplt.ui

import android.util.Log
import androidx.lifecycle.*
import com.example.testapplt.App
import kotlinx.coroutines.launch
import com.example.testapplt.data.model.LoginResponse
import com.example.testapplt.repos.Repository
import com.example.testapplt.utils.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _res = MutableLiveData<ResultWrapper<LoginResponse>>()
    val res: LiveData<ResultWrapper<LoginResponse>>
        get() = _res

    private val _navigateToSelectedProperty = MutableLiveData<String>()
    val navigateToSelectedProperty: LiveData<String>
        get() = _navigateToSelectedProperty


    fun login(email: String, pas: String) = viewModelScope.launch {
        when (val result = repository.callApiLogin(email, pas)) {
            is ResultWrapper.Success -> {
                _res.value = result
            }
            is ResultWrapper.Error -> {
                var exception: Exception? = result.error
                Log.d("ER", exception?.message.toString())
            }
        }

    }

    fun displayPropertyDetails(token: String) {
        _navigateToSelectedProperty.value = token
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
        _res.value = null
    }

}