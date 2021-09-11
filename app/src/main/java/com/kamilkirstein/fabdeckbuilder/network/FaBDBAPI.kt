package com.kamilkirstein.fabdeckbuilder.network

import androidx.annotation.Nullable
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
    @GET("cards?per_page=100")
    suspend fun getCards(): ListOfCards

    /* example for get with @Query to pass parameters to
   @GET("/maps/api/g
   eocode/json?")
    Call<JsonObject> getLocationInfo(@Query("address") String zipCode,
                                             @Query("sensor") boolean sensor,
                                             @Query("client") String client,
                                             @Query("signature") String signature);
     */

    @GET("cards?per_page=25")
    suspend fun getCardsForPage(@Query("page") page : Int) : ListOfCards

    @GET("cards?per_page=25")
    suspend fun getCardsOfSet(@Query("set") set : String?) : ListOfCards

}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object FabDbAPI {
    val retrofitService: FabDbApiService by lazy { retrofit.create(FabDbApiService::class.java) }
}
