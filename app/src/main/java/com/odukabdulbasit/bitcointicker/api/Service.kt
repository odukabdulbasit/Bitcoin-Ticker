package com.odukabdulbasit.bitcointicker.api


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


interface CoinApiService{

    @GET("api/v3/coins/list")
    suspend fun getCoinList(

    ) : String
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("https://api.coingecko.com/")
    .build()


object CoinApi {
    val retrofitCoinService: CoinApiService by lazy {
        retrofit.create(CoinApiService::class.java)
    }
}
