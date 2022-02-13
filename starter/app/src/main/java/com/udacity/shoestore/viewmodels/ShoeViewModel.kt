package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel: ViewModel() {
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
    get() {
        return _shoeList
    }

    init {
        //_shoeList.value = mutableListOf(Shoe("Nike", 42.0, "Nike","Nike shoe"))
        //_shoeList.value?.add(Shoe("Nike", 42.0, "Nike","Nike shoe"))
    }

    fun addShoe(shoe: Shoe) {
        if(_shoeList.value == null) {
            val list = mutableListOf<Shoe>()
            list.add(shoe)
            _shoeList.value = list

        }else{
            val list = _shoeList.value
            list?.add(shoe)
            _shoeList.value = list
        }

    }
}