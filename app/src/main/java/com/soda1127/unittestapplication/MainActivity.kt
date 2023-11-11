package com.soda1127.unittestapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.soda1127.unittestapplication.data.MainRepositoryImpl
import com.soda1127.unittestapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        viewModel = MainViewModel(
            mainRepository = MainRepositoryImpl(
                booksApiService = MainApplication.getBookApiService()
            )
        )

        viewModel.fetchData()

        viewModel.stateLiveData.observe(this) { state ->
            when(state) {
                is MainViewState.Loading -> Unit
                is MainViewState.Success -> Unit
                is MainViewState.Error -> Unit
                else -> Unit
            }
        }

    }

}
