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

    private val _shoeAddedState = MutableLiveData<Boolean?>()
    val shoeAddedState: LiveData<Boolean?>
        get() {
            return _shoeAddedState
        }

    private val _cancelState = MutableLiveData<Boolean?>()
    val cancelState: LiveData<Boolean?>
        get() {
            return _cancelState
        }

    private val _addShoeError = MutableLiveData<String?>()
    val addShoeError: LiveData<String?>
        get() {
            return _addShoeError
        }

    init {
    }

    fun addShoe(shoe: Shoe) {

        if (shoe.name.isNullOrEmpty()) {
            _addShoeError.value = "Name is required"
            return
        }

        if (shoe.company.isNullOrEmpty()) {
            _addShoeError.value = "Company is required"
            return
        }

        if (shoe.size == 0.0) {
            _addShoeError.value = "Enter a valid shoe size"
            return
        }

        if (shoe.description.isNullOrEmpty()) {
            _addShoeError.value = "Description is required"
            return
        }

        if(_shoeList.value == null) {
            val list = mutableListOf<Shoe>()
            list.add(shoe)
            _shoeList.value = list

        }else{
            val list = _shoeList.value
            list?.add(shoe)
            _shoeList.value = list
        }
        _shoeAddedState.value = true
    }

    fun cancel() {
        _cancelState.value = true
    }

    fun resetAddedState() {
        _shoeAddedState.value = null
    }

    fun resetCancelState() {
        _cancelState.value = null
    }

    fun resetAddShoeError() {
        _addShoeError.value = null
    }
}