package com.example.scratcher.ui.repositories.server

import com.example.scratcher.ui.repositories.server.api.O2Api
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivateServerRepository @Inject constructor(
    val O2Api: O2Api
) {

    suspend fun getVersion() = O2Api.getVersion()

}