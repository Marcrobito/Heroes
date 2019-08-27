package com.fintonic.heroes

import com.fintonic.heroes.network.provideClient
import com.fintonic.heroes.network.provideRetrofit
import com.fintonic.heroes.network.provideApiWebService
import com.fintonic.heroes.repository.HeroesContract
import com.fintonic.heroes.repository.HeroesRepository
import com.fintonic.heroes.viewmodel.HeroesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val heroModule = module{
    factory { HeroesRepository() as HeroesContract.Repository }
    viewModel { HeroesViewModel() }
}

val networkModule = module {
    single { provideClient() }
    single { provideRetrofit(get()) }
    single { provideApiWebService(get())}
}