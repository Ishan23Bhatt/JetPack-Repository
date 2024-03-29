package com.example.jettriviaapp.di

import android.util.Log
import com.example.jettriviaapp.network.QuestionsAPI
import com.example.jettriviaapp.repository.QuestionRepository
import com.example.jettriviaapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideQuestionRepository(api: QuestionsAPI) = QuestionRepository(api)

    @Singleton
    @Provides
    fun provideQuestionApi() : QuestionsAPI {


        try {
            val questionsAPI = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(QuestionsAPI::class.java)

            return questionsAPI
        }
        catch (exception: Exception){
            Log.e("asdf", "provideQuestionApi: ",exception )
        }
        
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionsAPI::class.java)
    }

}