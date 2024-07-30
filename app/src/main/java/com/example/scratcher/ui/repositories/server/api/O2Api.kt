package com.example.scratcher.ui.repositories.server.api

import com.example.scratcher.ui.entities.AndroidResponse
import retrofit2.http.GET

interface O2Api {

    @GET("https://api.o2.sk/version")
    suspend fun getVersion(): AndroidResponse

}