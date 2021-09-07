package com.febrian.wisataindonesia.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.febrian.wisataindonesia.data.entity.Model
import com.febrian.wisataindonesia.utils.DataDummy

class DataViewModel : ViewModel() {
    fun getData(): LiveData<ArrayList<Model>> {
        val data: MutableLiveData<ArrayList<Model>> = MutableLiveData<ArrayList<Model>>()
        data.postValue(DataDummy.getData())
        return data
    }
}