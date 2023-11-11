package com.soda1127.unittestapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soda1127.unittestapplication.data.MainRepository
import com.soda1127.unittestapplication.data.entity.BookEntity
import kotlinx.coroutines.launch


class MainViewModel(
    val mainRepository: MainRepository,
): ViewModel() {

    val stateLiveData: MutableLiveData<MainViewState> = MutableLiveData(MainViewState.Unitialized)

    fun fetchData() = viewModelScope.launch {
        stateLiveData.postValue(MainViewState.Loading)
        try {
            val bookList = mainRepository.fetchData()
            stateLiveData.postValue(MainViewState.Success(bookList))
        } catch (e: Exception) {
            stateLiveData.postValue(MainViewState.Error(e))
        }
    }

}


sealed class MainViewState {

    object Unitialized: MainViewState()

    object Loading: MainViewState()

    data class Success(val bookList: List<BookEntity>): MainViewState()

    data class Error(val exception: Exception): MainViewState()

}
