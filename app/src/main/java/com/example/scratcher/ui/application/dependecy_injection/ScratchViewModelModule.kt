package com.example.scratcher.ui.application.dependecy_injection

import androidx.lifecycle.ViewModel
import com.example.scratcher.ui.ui.ScratchViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap
import dagger.multibindings.IntoMap
import dagger.multibindings.ClassKey

@Module
@InstallIn(ViewModelComponent::class)
abstract class ScratchViewModelModule {

    @Binds
    @IntoMap
    @ClassKey(ScratchViewModel::class)
    @HiltViewModelMap
    abstract fun bindScratchViewModel(viewModel: ScratchViewModel): ViewModel
}