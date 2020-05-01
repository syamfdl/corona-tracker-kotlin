package me.syamfdl.coronatracker.services

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import me.syamfdl.coronatracker.data.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


const val BASE_URL = "https://api.kawalcorona.com/"

    val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

    private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

    // API Data
    interface ApiService {
        @GET("indonesia")
        suspend fun showData(): List<DataIndonesia>
        @GET("positif")
        suspend fun dataPositif(): DataPositifGlobal
        @GET("sembuh")
        suspend fun dataSembuh(): DataSembuhGlobal
        @GET("meninggal")
        suspend fun dataMeninggal(): DataMeninggalGlobal
        @GET("indonesia/provinsi")
        suspend fun dataProvinsi(): List<DataProvinsi>
    }

    object ApiCorona {
        val retrofitService = retrofit.create(ApiService::class.java)
    }


