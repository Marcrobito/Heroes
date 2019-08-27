package com.fintonic.heroes.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Hero(
    @SerializedName("name") @Expose val name:String,
    @SerializedName("photo") @Expose val photo:String,
    @SerializedName("realName") @Expose val realName:String,
    @SerializedName("height") @Expose val height:String,
    @SerializedName("power") @Expose val power:String,
    @SerializedName("abilities") @Expose val abilities:String,
    @SerializedName("groups") @Expose val groups:String
)

data class Superheros(
    @SerializedName("superheroes") @Expose val superheroes:List<Hero>
)