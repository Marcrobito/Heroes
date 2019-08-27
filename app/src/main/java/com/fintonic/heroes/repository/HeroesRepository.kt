package com.fintonic.heroes.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.toolbox.JsonObjectRequest
import com.fintonic.heroes.App
import com.fintonic.heroes.common.Constants
import com.fintonic.heroes.model.Hero
import com.fintonic.heroes.network.HeroesApi
import org.koin.core.KoinComponent
import org.koin.core.inject
import com.android.volley.Response
import com.fintonic.heroes.model.Superheros
import com.google.gson.Gson

class HeroesRepository:HeroesContract.Repository, KoinComponent {

    private val webservice: HeroesApi by inject()



    private lateinit var viewModel: HeroesContract.ViewModel

    override fun setViewModel(viewModel: HeroesContract.ViewModel) {
        this.viewModel = viewModel
        getSuperHeroes()
    }

    private fun getSuperHeroes(){

        /*Single.just(webservice.getHeroes().execute())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: DisposableSingleObserver<Response<Superheros>>(){
                override fun onSuccess(t: Response<Superheros>) {
                    if (t.isSuccessful){
                        superheros.value = t.body()!!.superheroes
                        viewModel.dataFetched(superheros)
                    }

                }

                override fun onError(e: Throwable) {

                }

            })*/
        val jsonRequest = object : JsonObjectRequest(
            Method.GET,
            Constants().URL,
            null,
            Response.Listener {
                val heroes = Gson().fromJson(it.toString(), Superheros::class.java)
                viewModel.dataFetched(heroes.superheroes)
            },
            Response.ErrorListener { error ->
                error.message

            }
        ) {
        }
        App.mRequestQueue.add(jsonRequest)
    }


}