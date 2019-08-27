package com.fintonic.heroes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fintonic.heroes.model.Hero
import com.fintonic.heroes.repository.HeroesContract
import org.koin.core.KoinComponent
import org.koin.core.inject

class HeroesViewModel: ViewModel(), HeroesContract.ViewModel, KoinComponent {

    private val repository:HeroesContract.Repository by inject()
    private var superheros : MutableLiveData<List<Hero>> = MutableLiveData()

    init {
        repository.setViewModel(this)
    }

    override fun dataFetched(heroes: List<Hero>) {
        superheros.value = heroes
    }

    fun getHeroes():MutableLiveData<List<Hero>> = superheros
}