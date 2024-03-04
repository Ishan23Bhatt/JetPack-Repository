package com.example.jettriviaapp.repository

import android.util.Log
import com.example.jettriviaapp.data.DataOrException
import com.example.jettriviaapp.model.QuestionsItem
import com.example.jettriviaapp.network.QuestionsAPI
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val api: QuestionsAPI) {

    private val dataOrException = DataOrException<ArrayList<QuestionsItem>,Boolean,Exception>()

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionsItem>, Boolean, java.lang.Exception> {
        try {

            dataOrException.loading = true
            try {
                dataOrException.data = api.getAllQuestions()
            }
            catch (exception : Exception){
                Log.e("asdf", "getAllQuestions: api.getAllQuestions() $exception")
            }

            if (dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false

        }catch (exception: Exception) {
            dataOrException.e = exception
            Log.e("asdf", "getAllQuestions: ${dataOrException.e!!.localizedMessage}")

        }
        return dataOrException
    }

}