package com.example.triforktestapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.triforktestapp.model.BiggestRepoModel
import kotlinx.coroutines.launch

class BiggestRepoViewModel : ViewModel() {

    private val biggestRepoModel = BiggestRepoModel()
    val data = MutableLiveData<Long?>()

    fun getBiggestRepo(organization: String) {
        viewModelScope.launch {
            var cursor: String? = null
            var maxValueInKB: Int
            var maxValueInNewPage: Int
            try {
                var response = biggestRepoModel.getRepos(organization, cursor)
                maxValueInKB =
                    response.data?.organization?.repositories?.nodes?.maxByOrNull { it?.diskUsage!! }?.diskUsage!!
                while (response.data?.organization?.repositories?.pageInfo?.hasNextPage!!) {
                    cursor = response.data?.organization?.repositories?.pageInfo?.endCursor
                    response = biggestRepoModel.getRepos(organization, cursor)
                    maxValueInNewPage =
                        response.data?.organization?.repositories?.nodes?.maxByOrNull { it?.diskUsage!! }?.diskUsage!!
                    if (maxValueInNewPage > maxValueInKB) {
                        maxValueInKB = maxValueInNewPage
                    }
                }
                val maxInBytes = maxValueInKB * 1024L
                Log.e("maxInBytes", maxInBytes.toString())
                data.postValue(maxInBytes)
            } catch (e: Exception) {
                data.postValue(null)
            }
        }
    }
}