package com.example.viewmodelkotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * カウンタを保持するだけのViewModel
 * android.arch.lifecycle.ViewModelProvider や、
 * android.arch.lifecycle.ViewModelProviders はもうメンテされないので
 * androidx.lifecycle.ViewModelProviders を使う
 */
class CountViewModel(val repositiry: SomeRepository): ViewModel() {
    // ViewModelに属するカウンター
    var counterB: Int = 0
}

/**
 * ViewModelのFactory
 * 引数なしなら、Factoryなくてもいけると思う
 */
class CountViewModelFactory(private val repositiry: SomeRepository): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CountViewModel(repositiry) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}