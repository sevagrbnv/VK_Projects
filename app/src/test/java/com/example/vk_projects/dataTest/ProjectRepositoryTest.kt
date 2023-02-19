package com.example.vk_projects.dataTest

import com.example.vk_projects.data.ProjectRepositoryImpl
import com.example.vk_projects.data.remoteDataSousce.Item
import com.example.vk_projects.domain.ProjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Test

class ProjectRepositoryTest {

    @Test
    fun getListTest_sizeIs8() = runBlocking {
        val repository: ProjectRepository = ProjectRepositoryImpl
        var result: List<Item>?
        result = withContext(Dispatchers.Default) { repository.getList() }
        Assert.assertEquals(result?.size, 8)
    }

    @Test
    fun getListTest_sizeIsNot9() = runBlocking {
        val repository: ProjectRepository = ProjectRepositoryImpl
        var result: List<Item>?
        result = withContext(Dispatchers.Default) { repository.getList() }
        Assert.assertNotEquals(result?.size, 9)
    }

    @Test
    fun getItemFromList_firstIsVK() = runBlocking {
        val repository: ProjectRepository = ProjectRepositoryImpl
        var result: List<Item>?
        result = withContext(Dispatchers.Default) { repository.getList() }
        val firstItemName = result[0].name
        Assert.assertEquals(firstItemName, "ВКонтакте")
    }
}