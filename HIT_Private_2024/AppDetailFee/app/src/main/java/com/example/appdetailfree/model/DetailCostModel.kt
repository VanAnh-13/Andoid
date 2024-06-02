package com.example.appdetailfree.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class DetailCostModel : ViewModel() {
    private val _listDetailCost = MutableLiveData<List<DetailCost>>()
    val listDetailCost: LiveData<List<DetailCost>> = _listDetailCost

    fun putData(data: DetailCost) =
        runBlocking {
            viewModelScope.async(Dispatchers.IO) {
                FireBaseCostService.putData(data)
            }.await()
        }

    init {
        viewModelScope.launch {
            _listDetailCost.postValue(FireBaseCostService.getData())
        }
    }
}