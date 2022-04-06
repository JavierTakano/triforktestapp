package com.example.triforktestapp.apollo

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.triforktestapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class ApolloInstance {

    private val githubGraphQLApiURL = "https://api.github.com/graphql"

    private val httpClient: OkHttpClient by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor).build()
    }

    fun get(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(githubGraphQLApiURL)
            .addHttpHeader("Authorization", "Bearer ${BuildConfig.GITHUB_TOKEN}")
            .okHttpClient(httpClient)
            .build()
    }

}