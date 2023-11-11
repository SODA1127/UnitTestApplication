package com.soda1127.unittestapplication

import android.app.Application
import com.soda1127.unittestapplication.data.buildOkHttpClient
import com.soda1127.unittestapplication.data.network.BooksApiService
import com.soda1127.unittestapplication.data.provideBookStoreRetrofit
import com.soda1127.unittestapplication.data.provideBooksApiService
import com.soda1127.unittestapplication.data.provideGsonConverterFactory

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val okHttpClient = buildOkHttpClient()
        val gsonConverterFactory = provideGsonConverterFactory()
        val retrofit = provideBookStoreRetrofit(okHttpClient, gsonConverterFactory)
        booksApiService = provideBooksApiService(retrofit)

    }

    companion object {

        lateinit var booksApiService : BooksApiService

        fun getBookApiService() = booksApiService

    }

}
