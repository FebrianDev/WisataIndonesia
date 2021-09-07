package com.febrian.wisataindonesia.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.febrian.wisataindonesia.R
import com.febrian.wisataindonesia.data.entity.Model
import com.febrian.wisataindonesia.databinding.ItemLayoutBinding
import com.febrian.wisataindonesia.ui.activity.DetailActivity

@SuppressLint("NotifyDataSetChanged")
class ListAdapter(private val list: ArrayList<Model>, private val activity : Activity?) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    companion object {
        const val KEY_DATA = "KEY_DATA"
    }

    inner class ViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(m: Model) {
            binding.title.text = m.name
            binding.location.text = m.tag

            Glide.with(itemView).load(m.images!![0])
                .error(R.drawable.ic_baseline_broken_image_24).into(binding.image)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(KEY_DATA, m)
                itemView.context.startActivity(intent)
                activity?.finish()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}