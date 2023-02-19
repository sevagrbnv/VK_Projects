package com.example.vk_projects.domain

import com.example.vk_projects.data.ProjectRepositoryImpl
import com.example.vk_projects.model.Item

class GetRemoteDataUseCase {

    private val repository: ProjectRepository = setRepository()

    private fun setRepository(): ProjectRepositoryImpl {
        return ProjectRepositoryImpl
    }

    suspend fun execute() : List<Item> {
        return repository.getList()
    }
}