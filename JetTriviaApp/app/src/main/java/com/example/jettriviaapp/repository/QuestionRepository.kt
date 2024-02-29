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
            dataOrException.data = api.getAllQuestions()
            if (dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false

        }catch (exception: Exception) {
            dataOrException.e = exception
            Log.d("Exc", "getAllQuestions: ${dataOrException.e!!.localizedMessage}")

        }
        return dataOrException
    }

}