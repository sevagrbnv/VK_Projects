package com.example.vk_projects.domain

import com.example.vk_projects.data.remoteDataSousce.Item

interface ProjectRepository {

    suspend fun getList(): List<Item>

    fun getItemFromList(index: Int): Item
}