package com.odukabdulbasit.bitcointicker.api


import com.odukabdulbasit.bitcointicker.detail.DetailModel
import com.odukabdulbasit.bitcointicker.listsearch.ListSearchModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface CoinApiService{

    @GET("api/v3/coins/list")
    suspend fun getCoinList(

    ) : List<ListSearchModel>


    @GET("api/v3/coins/markets")
    suspend fun getCoinDetail(

        @Query("vs_currency") vs_currency: String,
        @Query("ids") ids: String

    ) : List<DetailModel>
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
