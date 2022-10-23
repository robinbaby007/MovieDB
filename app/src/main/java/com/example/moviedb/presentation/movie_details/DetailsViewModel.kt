package com.example.moviedb.presentation.movie_details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedb.domain.use_cases.UseCases
import com.google.gson.Gson
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(useCases: UseCases):ViewModel() {

init {
    viewModelScope.launch {


        Gson().toJson(useCases.movieDetails.invoke("550").collect(){
            Log.e("cDetailz", Gson().toJson(it.body()) )

        })

        Gson().toJson(useCases.castList.invoke("550").collect(){
            Log.e("cDetailz", Gson().toJson(it.body()) )

        })
    }
}
}