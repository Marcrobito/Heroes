package com.fintonic.heroes.network

import com.fintonic.heroes.model.Superheros
import retrofit2.Call
import retrofit2.http.GET

interface HeroesApi {
    @GET("bvyob")
    fun getHeroes(): Call<Superheros>
}