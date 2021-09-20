package com.kamilkirstein.fabdeckbuilder.network

import androidx.annotation.Nullable
import com.kamilkirstein.fabdeckbuilder.datafilter.CardFilter
import com.kamilkirstein.fabdeckbuilder.network.data.ListOfCards
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://api.fabdb.net/"

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class NullToEmptyString

/**
 * create a NulltoEmptyStringAdapter
 */
class NullToEmptyStringAdapter {
    @ToJson
    fun toJson(@NullToEmptyString value: String?): String? {
        return value
    }

    @FromJson
    @NullToEmptyString
    fun fromJson(@Nullable data: String?): String? {
        return data ?: ""
    }
}

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
 */
private val moshi = Moshi.Builder()
    .add(NullToEmptyStringAdapter())
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
    @GET("cards?per_page=25")
    suspend fun getCards(
        @Query("page") page: Int,
        @Query("name") name: String?,
        @Query("set") set: String?,
        @Query("keywords") keywords: MutableSet<String>?,
        @Query("pitch") pitch: String?,
        @Query("cost") cost: String?,
        @Query("rarity") rarity: String?
    ): ListOfCards

}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object FabDbAPI {
    val retrofitService: FabDbApiService by lazy { retrofit.create(FabDbApiService::class.java) }
}
