package com.user.ingeweb.alcalist_mobile_wms_new.di

import android.app.Application
import android.content.Context
import com.user.ingeweb.alcalist_mobile_wms_new.api.ApplicationApi
import com.user.ingeweb.alcalist_mobile_wms_new.caseuses.MultiResponseConverterFactory
import com.user.ingeweb.alcalist_mobile_wms_new.utils.LiveDataCallAdapterFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class AppModule {
    @Singleton
    @Provides
    fun provideApplicationApi(): ApplicationApi {
        return Retrofit.Builder()
            .baseUrl("http://awsbr.ovall.com.co/api/")
            .addConverterFactory(GsonConverterFactory.create()) //MultiResponseConverterFactory.create()
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(ApplicationApi::class.java)
    }

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}