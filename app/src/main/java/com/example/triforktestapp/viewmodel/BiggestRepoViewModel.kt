package com.example.triforktestapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.example.triforktestapp.BiggestRepoQuery
import com.example.triforktestapp.model.BiggestRepoModel
import kotlinx.coroutines.launch

class BiggestRepoViewModel : ViewModel() {

    private val biggestRepoModel = BiggestRepoModel()
    val data = MutableLiveData<Long?>()

    fun getBiggestRepo(organization: String) {
        viewModelScope.launch {
            val cursor = null
            try {
                val response = biggestRepoModel.getRepos(organization, cursor)
                val biggestRepo = response.data?.organization?.repositories?.nodes?.maxByOrNull { it?.diskUsage!! }
                val maxInKB = biggestRepo!!.diskUsage
                val maxInBytes = maxInKB!!*1024L
                data.postValue(maxInBytes)
            } catch (e: Exception) {
            }
        }
    }
}