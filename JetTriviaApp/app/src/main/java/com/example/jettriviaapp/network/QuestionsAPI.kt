package com.example.jettriviaapp.network

import com.example.jettriviaapp.model.QuestionsItem
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionsAPI {

    @GET("world.json")
    suspend fun getAllQuestions(): ArrayList<QuestionsItem>

}