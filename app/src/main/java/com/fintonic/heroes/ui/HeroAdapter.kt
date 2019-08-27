package com.fintonic.heroes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fintonic.heroes.R
import com.fintonic.heroes.model.Hero
import kotlinx.android.synthetic.main.fragment_hero_card.view.*
import kotlinx.android.synthetic.main.hero_card.view.*


class HeroAdapter(private val heroes: List<Hero>, private val listener: OnHeroSelectedListener):RecyclerView.Adapter<HeroAdapter.ViewHolder>(), OnHeroSelectedListener{



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent, this)

    override fun getItemCount() = heroes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(heroes[position])
    }

    override fun onHeroSelected(position: Int) {
        listener.onHeroSelected(position)
    }


    class ViewHolder(parent: ViewGroup, private val listener: OnHeroSelectedListener) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.hero_card, parent, false)) {

        fun bind(hero:Hero) = with(itemView){
            val powers = "powers: ${hero.power}"
            heroName.text = hero.name
            heroPower.text = powers

            Glide.with(context).load(hero.photo).into(heroImage)
            heroButton.setOnClickListener {
                listener.onHeroSelected(adapterPosition)
            }
        }
    }
}

interface OnHeroSelectedListener {
    fun onHeroSelected(position:Int)
}