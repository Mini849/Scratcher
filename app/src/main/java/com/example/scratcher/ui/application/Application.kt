package com.example.scratcher.ui.application

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration

import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import javax.inject.Inject

@HiltAndroidApp
class Application : Application() {

}

