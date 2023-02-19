package com.example.vk_projects.domain

import com.example.vk_projects.data.ProjectRepositoryImpl
import com.example.vk_projects.data.remoteDataSousce.Item

class GetItemFromListUseCase {

    private val repository: ProjectRepository = setRepository()

    private fun setRepository(): ProjectRepositoryImpl {
        return ProjectRepositoryImpl
    }

    fun execute(number: Int) : Item {
        return repository.getItemFromList(number)
    }
}