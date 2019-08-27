package com.fintonic.heroes.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fintonic.heroes.model.Hero
import com.fintonic.heroes.model.Superheros

interface HeroesContract {

    interface Repository{
        fun setViewModel(viewModel: ViewModel)
    }

    interface ViewModel{
        fun dataFetched(heroes: List<Hero>)
    }
}