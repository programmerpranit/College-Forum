package com.psp.collegeforum.di

import android.content.Context
import android.content.SharedPreferences
import com.psp.collegeforum.data.BackendApi
import com.psp.collegeforum.data.repositories.MainRepo
import com.psp.collegeforum.util.Constants.BASE_URL
import com.psp.collegeforum.util.Constants.KEY_JWT
import com.psp.collegeforum.util.Constants.USER_SHARED_PREFERENCE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    // Provides Shared Preference so we can add our jwt token in share fragment
    @Singleton
    @Provides
    fun provideSharedPreference(
        @ApplicationContext app: Context
    ) = app.getSharedPreferences(USER_SHARED_PREFERENCE, Context.MODE_PRIVATE)

    // to get the jwt key that we stored inside shared pref
    @Singleton
    @Provides
    fun provideJWTKey(sharedPreferences: SharedPreferences) =
        sharedPreferences.getString(KEY_JWT, "")?:""

    @Provides
    @Singleton
    fun provideBackendApi(): BackendApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BackendApi::class.java)

    @Provides
    @Singleton
    fun provideMainRepo(api: BackendApi): MainRepo = MainRepo(api)


}