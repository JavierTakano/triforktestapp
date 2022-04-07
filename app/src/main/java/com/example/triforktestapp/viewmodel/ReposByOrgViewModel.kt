package com.example.triforktestapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.example.triforktestapp.ReposByOrgQuery
import com.example.triforktestapp.model.ReposByOrgModel
import kotlinx.coroutines.launch

class ReposByOrgViewModel : ViewModel() {

    private val reposByOrgModel = ReposByOrgModel()
    val data = MutableLiveData<ApolloResponse<ReposByOrgQuery.Data>>()

    fun updateReposOrg(organization: String) {
        viewModelScope.launch {
            try {
                val response = reposByOrgModel.getReposForOrg(organization)
                data.postValue(response)
            } catch (e: Exception) {
            }
        }
    }
}