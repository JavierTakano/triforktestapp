package com.example.triforktestapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.example.triforktestapp.OrgTotalQuery
import com.example.triforktestapp.model.OrgTotalModel
import kotlinx.coroutines.launch

class OrgTotalViewModel : ViewModel() {

    private val orgTotalModel = OrgTotalModel()
    val data = MutableLiveData<ApolloResponse<OrgTotalQuery.Data>>()

    fun updateTotal() {
        viewModelScope.launch {
            try {
                val response = orgTotalModel.getOrgTotal()
                data.postValue(response)
            } catch (e: Exception) {
            }
        }
    }
}