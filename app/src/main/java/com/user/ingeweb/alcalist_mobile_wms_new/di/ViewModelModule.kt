package com.user.ingeweb.alcalist_mobile_wms_new.di

import androidx.lifecycle.ViewModel
import com.user.ingeweb.alcalist_mobile_wms_new.ui.task.TaskViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class ViewModelModule {
    @Binds
    abstract fun bindTaskViewModel(taskViewModel: TaskViewModel): ViewModel
}