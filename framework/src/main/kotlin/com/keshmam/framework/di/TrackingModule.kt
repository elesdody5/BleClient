package com.keshmam.framework.di

import com.keshmam.core.tracking.Logger
import com.keshmam.framework.tracking.LoggerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TrackingModule {

    @Provides
    @Singleton
    fun provideLogger():Logger{
        return LoggerImpl()
    }


}