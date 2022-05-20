package ru.mirea.sipirecipes.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.mirea.sipirecipes.BuildConfig
import ru.mirea.sipirecipes.data.network.RecipeService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRecipeApiService(retrofit: Retrofit): RecipeService =
        retrofit.create(RecipeService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BACKEND_URL_REMOTE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}