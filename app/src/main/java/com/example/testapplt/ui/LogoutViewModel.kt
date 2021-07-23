package com.example.testapplt.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplt.data.model.LogoutResponse
import com.example.testapplt.repos.Repository
import com.example.testapplt.utils.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _res = MutableLiveData<ResultWrapper<LogoutResponse>>()
    val res: LiveData<ResultWrapper<LogoutResponse>>
        get() = _res

    // пытвлся сделать запрос но я не совсем понял из доки что должно в нем быть
    // и что должно приходить в ответ

    fun logout(token: String) = viewModelScope.launch {
        when (val result = repository.callApiLogout("")) {
            is ResultWrapper.Success -> {
                Log.d("out", result.value.toString())
                _res.value = result
            }
            is ResultWrapper.Error -> {
                var exception: Exception? = result.error
                Log.d("ER", exception?.message.toString())
            }
        }

    }
}