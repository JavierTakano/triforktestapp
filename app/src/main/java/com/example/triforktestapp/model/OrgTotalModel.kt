package com.example.triforktestapp.model

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.example.triforktestapp.OrgTotalQuery
import com.example.triforktestapp.apollo.ApolloInstance

class OrgTotalModel {

    private lateinit var client: ApolloClient

    suspend fun getOrgTotal(): ApolloResponse<OrgTotalQuery.Data> {
        client = ApolloInstance().get()
        return client.query(OrgTotalQuery()).execute()
    }
}