package com.fintonic.heroes.view


import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.fintonic.heroes.R
import com.fintonic.heroes.model.Hero
import kotlinx.android.synthetic.main.fragment_hero_card.*
import kotlinx.android.synthetic.main.fragment_hero_card.view.*
import kotlinx.android.synthetic.main.fragment_hero_card.view.heroPhoto
import kotlinx.android.synthetic.main.hero_card.view.*


class HeroCard : Fragment() {

    private lateinit var listener: HeroListener

    interface HeroListener{
        fun closeCard()
    }

    override fun onAttach(context: Context?) {
        if (context is HeroListener)
            listener = context
        else
            throw Exception("You must implement HeroListener")
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_hero_card, container, false)
        v.closeCardButton.setOnClickListener {
            listener.closeCard()
        }
        val photo = arguments!!.getString("photo")
        val name = arguments!!.getString("name")
        val powers = arguments!!.getString("power")
        val realName = arguments!!.getString("realName")
        val height = "Height: " + arguments!!.getString("height")
        val abilities = arguments!!.getString("abilities")
        val groups = arguments!!.getString("groups")
        v.heroNameTextView.text = name
        v.heroPowersTextView.text = powers
        v.realNameTextView.text = realName
        v.heroHeightTextView.text = height
        v.heroAbilitiesTextView.text = abilities
        v.heroGroupsTextView.text = groups
        Glide.with(activity!!).load(photo).into(v.heroPhoto)

        activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        return v
    }


}


