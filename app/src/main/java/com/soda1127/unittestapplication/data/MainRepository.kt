package com.soda1127.unittestapplication.data

import com.soda1127.unittestapplication.data.entity.BookEntity
import com.soda1127.unittestapplication.data.network.BooksApiService

interface MainRepository {
    
    suspend fun fetchData(): List<BookEntity>
    
}

class MainRepositoryImpl(
    val booksApiService: BooksApiService
): MainRepository {

    override suspend fun fetchData(): List<BookEntity> {
        val response = booksApiService.getNewBooks()
        if(response.isSuccessful) {
            return response.body()?.books ?: listOf()
        } else throw Exception(response.message())
    }

}
