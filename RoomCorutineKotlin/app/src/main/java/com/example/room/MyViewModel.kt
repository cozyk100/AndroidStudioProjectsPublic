package com.example.room

import androidx.lifecycle.*
import com.example.room.entity.Dept
import com.example.room.entity.User
import com.example.room.entity.UserView
import kotlinx.coroutines.launch

/**
 * ViewModel
 */
class MyViewModel(private val repository: MyRepository): ViewModel() {

    /**
     * UserViewのSELECT
     */
    val userViewList: LiveData<List<UserView>> = repository.userViewList.asLiveData()

    /**
     * UserのINSERT
     */
    fun insertUser(user: User) = viewModelScope.launch {
        repository.insertUser(user)
    }

    /**
     * DEPTのINSERT
     */
    fun insertDept(dept: Dept) = viewModelScope.launch {
        repository.insertDept(dept)
    }
}

/**
 * Factory
 */
class MyViewModelFactory(private val repository: MyRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViweModel class")
    }
}