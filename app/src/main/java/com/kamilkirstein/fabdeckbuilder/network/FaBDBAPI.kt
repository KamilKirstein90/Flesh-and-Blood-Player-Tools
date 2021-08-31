package com.kamilkirstein.fabdeckbuilder.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://api.fabdb.net/"

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * The Retrofit object with the Moshi converter.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getCards] method
 */
interface FabDbApiService {
    /**
     * Returns a [List] of [Cards] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "photos" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("cards")
    suspend fun getCards(): ListOfCards
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object FabDbAPI {
    val retrofitService: FabDbApiService by lazy { retrofit.create(FabDbApiService::class.java) }
}
