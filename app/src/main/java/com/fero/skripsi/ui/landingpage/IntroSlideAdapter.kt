package com.fero.skripsi.ui.landingpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fero.skripsi.R


class IntroSlideAdapter(private val introSlides: List<IntroSlide>): RecyclerView.Adapter<IntroSlideAdapter.IntroSlideViewHolder>() {
    inner class IntroSlideViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val textTitle = view.findViewById<TextView>(R.id.tv_intro_title)

        private val textDesc = view.findViewById<TextView>(R.id.tv_intro_desc)

        private val imageIcon = view.findViewById<ImageView>(R.id.img_slide_intro)

        fun bind(introSlide: IntroSlide){
            textTitle.text = introSlide.title
            textDesc.text = introSlide.description
            imageIcon.setImageResource(introSlide.image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSlideViewHolder {
        return  IntroSlideViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slide_item_container_intro,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: IntroSlideViewHolder, position: Int) {
        holder.bind(introSlides[position])
    }

    override fun getItemCount(): Int {
        return  introSlides.size
    }
}












