package com.fintonic.heroes.view

import android.content.Context
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Surface
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fintonic.heroes.R
import com.fintonic.heroes.model.Hero
import com.fintonic.heroes.ui.HeroAdapter
import com.fintonic.heroes.ui.OnHeroSelectedListener
import com.fintonic.heroes.viewmodel.HeroesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel



class Heroes : AppCompatActivity(), HeroCard.HeroListener, OnHeroSelectedListener {

    private val viewModel: HeroesViewModel by viewModel()

    private lateinit var heroes:List<Hero>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getHeroes().observe(this, Observer {
            if (progressBar.visibility == View.VISIBLE)
                progressBar.visibility = View.GONE

            heroes = it
            val adapter = HeroAdapter(heroes, this)
            val manager = LinearLayoutManager(this, getRotation(), false)
            heroRecycler.adapter = adapter
            heroRecycler.layoutManager = manager


        })
    }

    private fun getRotation(): Int{
        val display = (baseContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay

        return when(display.rotation){
            Surface.ROTATION_90 ->  RecyclerView.HORIZONTAL
            Surface.ROTATION_270 ->  RecyclerView.HORIZONTAL
            else -> RecyclerView.VERTICAL
        }
    }

    override fun closeCard() {
        for (fragment in supportFragmentManager.fragments) {
            if (fragment is HeroCard)
                supportFragmentManager.beginTransaction().remove(fragment).commit()
        }
        //supportFragmentManager.beginTransaction().remove(heroCard).commit()
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }


    override fun onHeroSelected(position: Int) {
        //heroCard.setHero(heroes[position], this)
        val heroCard = HeroCard()
        val bundle = Bundle()
        bundle.putString("photo", heroes[position].photo)
        bundle.putString("abilities", heroes[position].abilities)
        bundle.putString("groups", heroes[position].groups)
        bundle.putString("height", heroes[position].height)
        bundle.putString("name", heroes[position].name)
        bundle.putString("power", heroes[position].power)
        bundle.putString("realName", heroes[position].realName)
        heroCard.arguments = bundle

        supportFragmentManager.beginTransaction().add(R.id.container, heroCard).commit()
    }



}
