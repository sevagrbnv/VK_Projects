package com.example.vk_projects.data

import com.example.vk_projects.data.remoteDataSousce.ApiService
import com.example.vk_projects.model.Item
import com.example.vk_projects.domain.ProjectRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProjectRepositoryImpl : ProjectRepository {

    private var apiService: ApiService = setRetrofit()

    private fun setRetrofit(): ApiService {
        val logging = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        apiService = Retrofit.Builder()
            .baseUrl(ApiService.getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)

        return apiService
    }

    private lateinit var list: List<Item>

    private suspend fun getRemoteData() {
        val result = apiService.getResponce()
        if (result.isSuccessful)
            list = result.body()?.items ?: emptyList()
    }

    override suspend fun getList(): List<Item> {
        getRemoteData()
        return list
    }

    override fun getItemFromList(index: Int) = list[index]
}