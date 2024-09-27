package com.example.penncodingchallenge

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.penncodingchallenge.models.AqiData
import com.example.penncodingchallenge.models.LocationState
import com.example.penncodingchallenge.utils.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class HomeViewModel: ViewModel() {
    private val uiState = MutableStateFlow(LocationState())
    val state: StateFlow<LocationState> = uiState;

    private val _aqiData = MutableStateFlow(AqiData())
    val aqiData : StateFlow<AqiData> = _aqiData

    fun updateLocation(latitude: Double, longitude: Double) {
        uiState.update { it.copy(latitude, longitude) }
    }

    private fun retrieveAqiByCity() {
        viewModelScope.launch {
            val call : Call<AqiData> =
                RetrofitInstance.api.getAqiWithCity("newyork", "dae702f98ec0f3a68a36b6b49b327b4c781727e9")
            call.enqueue(object : Callback<AqiData> {
                override fun onResponse(
                    call: Call<AqiData>,
                    response: Response<AqiData>
                ) {
                    if(response.isSuccessful) {
                        val responseData: AqiData? = response.body()
                        if (responseData != null) {
                            _aqiData.value = responseData
                        }
                    }
                }
                override fun onFailure(call: Call<AqiData>, response: Throwable) {
                    Log.d("Failed to retrieve data", "Network Error")
                }
            })
        }
    }
}