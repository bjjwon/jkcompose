package com.jobkorea.app.di

import com.jobkorea.app.services.DispatcherProvider
import com.jobkorea.app.services.DispatcherProviderImpl
import com.jobkorea.app.services.PreferenceProvider
import com.jobkorea.app.services.PreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindDispatcherProvider(
        dispatcherProvider: DispatcherProviderImpl
    ) : DispatcherProvider

    @Binds
    abstract fun bindSharedPreference(
        sharedPreference: PreferencesImpl
    ) : PreferenceProvider

}