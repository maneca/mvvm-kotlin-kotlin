package com.example.mvvm_kotlin_kotlin.repository

import android.content.Context
import android.util.Log
import com.example.mvvm_kotlin_kotlin.api.CountriesApi
import com.example.mvvm_kotlin_kotlin.db.dao.CountriesDao
import com.example.mvvm_kotlin_kotlin.db.model.CountriesData
import com.example.mvvm_kotlin_kotlin.utils.AppResult
import com.example.mvvm_kotlin_kotlin.utils.NetworkManager.isOnline
import com.example.mvvm_kotlin_kotlin.utils.TAG
import com.example.mvvm_kotlin_kotlin.utils.Utils.handleApiError
import com.example.mvvm_kotlin_kotlin.utils.Utils.handleSuccess
import com.example.mvvm_kotlin_kotlin.utils.noNetworkConnectivityError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class CountriesRepositoryImp(
    private val api: CountriesApi,
    private val context: Context,
    private val dao: CountriesDao
) : CountriesRepository{

    override suspend fun getAllCountries(): AppResult<List<CountriesData>> {
        if(isOnline(context)){
            return try {
                val response = api.getAllCountries()

                if (response.isSuccessful){
                   response.body()?.let {
                       withContext(Dispatchers.IO) { dao.add(it)}
                   }
                    handleSuccess(response)
                } else{
                    handleApiError(response)
                }
            }catch (e: Exception) {
                AppResult.Error(e)
            }
        }else{
            val data = getCountriesDataFromCache()
            return if(data.isNotEmpty()){
                Log.d(TAG, "from fb")
                AppResult.Success(data)
            }else{
                context.noNetworkConnectivityError()
            }
        }
    }

    private suspend fun getCountriesDataFromCache(): List<CountriesData>{
        return withContext(Dispatchers.IO) { dao.findAll() }
    }

}