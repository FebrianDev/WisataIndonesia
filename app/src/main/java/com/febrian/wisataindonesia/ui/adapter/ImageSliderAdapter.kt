package com.febrian.wisataindonesia.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.febrian.wisataindonesia.databinding.ImageSliderLayoutItemBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class ImageSliderAdapter(private val listImages: ArrayList<Int>) :
    SliderViewAdapter<ImageSliderAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ImageSliderLayoutItemBinding) :
        SliderViewAdapter.ViewHolder(binding.root) {
        fun bind(s: Int) {
            Glide.with(itemView).load(s).into(binding.ivAutoImageSlider)
        }
    }

    override fun getCount(): Int {
        return listImages.size
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view =
            ImageSliderLayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(listImages[position])
    }
}