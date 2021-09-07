package com.febrian.wisataindonesia.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.febrian.wisataindonesia.data.database.DataDao
import com.febrian.wisataindonesia.data.database.DatabaseRoom
import com.febrian.wisataindonesia.data.entity.Model
import com.febrian.wisataindonesia.databinding.FragmentFavoriteBinding
import com.febrian.wisataindonesia.ui.adapter.ListAdapter
import com.febrian.wisataindonesia.ui.viewmodel.DataViewModel

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private var c: Context? = null
    private lateinit var viewModel: DataViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        c = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private lateinit var database: DataDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = DatabaseRoom.getDatabase(c!!.applicationContext).dataDao()

        viewModel = ViewModelProvider(this).get(DataViewModel::class.java)
        viewModel.getData().observe(requireActivity(), { t ->
            binding.rv.layoutManager = LinearLayoutManager(c)
            val list = getData(t)
            binding.rv.adapter = ListAdapter(list, requireActivity())
        })

    }

    private fun getData(t : ArrayList<Model>) : ArrayList<Model>{
        val list = ArrayList<Model>()
        val listData = database.getAllData()
        for (i in listData.indices) {
            for (j in 0 until t.size) {
                if (t[j].name == listData[i].name) {
                    list.add(t[j])
                }
            }
        }
        return list
    }
}