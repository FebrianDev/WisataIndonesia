package com.febrian.wisataindonesia.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.febrian.wisataindonesia.databinding.FragmentHomeBinding
import com.febrian.wisataindonesia.ui.adapter.ListAdapter
import com.febrian.wisataindonesia.ui.viewmodel.DataViewModel


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel : DataViewModel

    private var c : Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        c = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DataViewModel::class.java)
        viewModel.getData().observe(requireActivity(), {
            binding.rv.layoutManager = LinearLayoutManager(c)
            binding.rv.adapter = ListAdapter(it, requireActivity())
        })

    }
}