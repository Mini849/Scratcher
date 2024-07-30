package com.example.scratcher.ui.application.dependecy_injection

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AppUnauthorizedHttpClient()
