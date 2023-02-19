package com.example.vk_projects.presentation.detailFragment

import androidx.lifecycle.ViewModel
import com.example.vk_projects.data.remoteDataSousce.Item
import com.example.vk_projects.domain.GetItemFromListUseCase

class DetailViewModel(): ViewModel() {

    private val getItemFromList = GetItemFromListUseCase()

    fun getItem(number: Int) : Item = getItemFromList.execute(number)
}