package com.example.vk_projects.data.remoteDataSousce

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/semi-final-data.json")
    suspend fun getResponce(): Response<ListData>

    companion object {
        fun getBaseUrl() = "https://mobile-olympiad-trajectory.hb.bizmrg.com"
    }
}