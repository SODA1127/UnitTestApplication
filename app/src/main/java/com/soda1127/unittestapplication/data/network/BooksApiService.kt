package com.soda1127.unittestapplication.data.network

import com.soda1127.unittestapplication.data.response.BookStoreNewResponse
import com.soda1127.unittestapplication.data.url.BookStoreUrl
import retrofit2.Response
import retrofit2.http.GET

interface BooksApiService {

    @GET(BookStoreUrl.EndPoint.NEW)
    suspend fun getNewBooks(): Response<BookStoreNewResponse>

}
