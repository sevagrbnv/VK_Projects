package com.example.vk_projects.domainTest

import com.example.vk_projects.data.ProjectRepositoryImpl
import com.example.vk_projects.data.remoteDataSousce.Item
import com.example.vk_projects.domain.GetRemoteDataUseCase
import com.example.vk_projects.domain.ProjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Test

class GetRemoteDataUseCaseTest {

    @Test
    fun getRemoteDataUseCaseTest_isEquals() = runBlocking {
        val repository: ProjectRepository = ProjectRepositoryImpl
        var repResult: List<Item>?
        repResult = withContext(Dispatchers.Default) { repository.getList() }

        val getRemoteDataUseCase = GetRemoteDataUseCase()
        val useCaseResult = getRemoteDataUseCase.execute()

        Assert.assertEquals(repResult, useCaseResult)
    }

    @Test
    fun getRemoteDataUseCaseTest_isNotEquals() = runBlocking {
        val repository: ProjectRepository = ProjectRepositoryImpl
        var repResult: List<Item>?
        repResult = withContext(Dispatchers.Default) { repository.getList() }
        repResult = repResult.subList(2,5)

        val getRemoteDataUseCase = GetRemoteDataUseCase()
        val useCaseResult = getRemoteDataUseCase.execute()

        Assert.assertNotEquals(repResult, useCaseResult)
    }
}