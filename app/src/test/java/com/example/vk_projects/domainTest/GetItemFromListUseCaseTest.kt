package com.example.vk_projects.domainTest

import com.example.vk_projects.data.ProjectRepositoryImpl
import com.example.vk_projects.model.Item
import com.example.vk_projects.domain.GetItemFromListUseCase
import com.example.vk_projects.domain.ProjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Test

class GetItemFromListUseCaseTest {

    @Test
    fun getItem_isEquals() = runBlocking {
        val repository: ProjectRepository = ProjectRepositoryImpl
        var repResult: Item
        repository.getList()
        repResult = withContext(Dispatchers.Default) {
            repository.getItemFromList(0)
        }

        val getItemFromListUseCase = GetItemFromListUseCase()
        val useCaseResult = getItemFromListUseCase.execute(0)

        Assert.assertEquals(repResult, useCaseResult)
    }

    @Test
    fun getItem_isNotEquals() = runBlocking {
        val repository: ProjectRepository = ProjectRepositoryImpl
        var repResult: Item
        repository.getList()
        repResult = withContext(Dispatchers.Default) {
            repository.getItemFromList(1)
        }

        val getItemFromListUseCase = GetItemFromListUseCase()
        val useCaseResult = getItemFromListUseCase.execute(0)

        Assert.assertNotEquals(repResult, useCaseResult)
    }
}