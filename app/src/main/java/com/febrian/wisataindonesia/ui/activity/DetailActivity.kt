package com.febrian.wisataindonesia.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.febrian.wisataindonesia.*
import com.febrian.wisataindonesia.data.database.DataDao
import com.febrian.wisataindonesia.data.database.DataRoom
import com.febrian.wisataindonesia.data.database.DatabaseRoom
import com.febrian.wisataindonesia.data.entity.Model
import com.febrian.wisataindonesia.databinding.ActivityDetailBinding
import com.febrian.wisataindonesia.ui.adapter.ImageSliderAdapter
import com.febrian.wisataindonesia.ui.adapter.ListAdapter.Companion.KEY_DATA
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var database: DataDao

    private var data: Model? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data = intent.getParcelableExtra<Model>(KEY_DATA) as Model

        database = DatabaseRoom.getDatabase(applicationContext).dataDao()

        data!!.images?.let { ImageSliderAdapter(it) }
            ?.let { binding.imgSlider.setSliderAdapter(it) }

        val exist = database.dataExist(data!!.name.toString())
        setIcon(exist)

        binding.title.text = data!!.name.toString()
        binding.description.text = data!!.description.toString()
        binding.tag.text = data!!.tag.toString()

        binding.btnFavorite.setOnClickListener(this)
        binding.back.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnFavorite -> {
                addFavorite(v)
            }
            binding.back -> {
                back()
            }
        }
    }

    private fun addFavorite(v : View) {
        val exist = database.dataExist(data!!.name.toString())
        setIcon(exist, v)
    }

    private fun setIcon(exist: Boolean, v : View) {
        if (!exist) {
            Snackbar.make(v, "Success Add to Favorite", Snackbar.LENGTH_SHORT).show()
            binding.btnFavorite.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
            val newData = DataRoom(
                name = data!!.name,
                address = data!!.address,
                description = data!!.description,
                website = data!!.website,
                tag = data!!.tag,
                img = data!!.images?.get(0)
            )
            database.insert(newData)
        } else {
            Snackbar.make(v, "Success Delete to Favorite", Snackbar.LENGTH_SHORT).show()
            binding.btnFavorite.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
            database.delete(data!!.name.toString())
        }
    }

    private fun setIcon(exist : Boolean){
        if(exist){
            binding.btnFavorite.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        }else{
            binding.btnFavorite.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun back() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}