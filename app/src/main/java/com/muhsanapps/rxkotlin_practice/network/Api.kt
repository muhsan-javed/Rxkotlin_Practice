package com.muhsanapps.rxkotlin_practice.network

import com.muhsanapps.rxkotlin_practice.models.Food
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import java.util.*

interface Api {

    @GET("photos")
    fun getAllData():Observable <List<Food>>
}