package com.example.vk_projects.presentation.mainFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_projects.model.Item
import com.example.vk_projects.domain.GetRemoteDataUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel() : ViewModel() {

    private val getRemoteDataUseCase = GetRemoteDataUseCase()

    init {
        viewModelScope.launch { downloadData() }
    }

    private val _dataLD = MutableLiveData<List<Item>?>()
    val dataLD: LiveData<List<Item>?>
        get() = _dataLD

    private suspend fun downloadData() {
        var result: List<Item>? = null
        withContext(viewModelScope.coroutineContext) {
            result = getRemoteDataUseCase.execute()
        }
        _dataLD.value = result
    }
}