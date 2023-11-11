package com.soda1127.unittestapplication

import com.soda1127.unittestapplication.data.MainRepository
import com.soda1127.unittestapplication.data.MainRepositoryImpl
import com.soda1127.unittestapplication.data.entity.BookEntity
import com.soda1127.unittestapplication.data.network.BooksApiService
import com.soda1127.unittestapplication.data.response.BookStoreNewResponse
import com.soda1127.unittestapplication.testbase.JUnit5Test
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import retrofit2.Response
import java.util.stream.Stream

@Suppress("NonAsciiCharacters")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExperimentalCoroutinesApi
class MainViewModelTest: JUnit5Test() {

    private lateinit var sut: MainViewModel

    @MockK
    lateinit var booksApiService: BooksApiService

    private fun makeNewBooks() = (0 until 10).map { int ->
        BookEntity(
            title = "title $int",
            subtitle = "subtitle $int",
            price = int.toString(),
            image = "",
            url = "",
        )
    }

    private fun createNewResponse(bookList: List<BookEntity>) = BookStoreNewResponse(
        error = "error",
        total = "total",
        books = bookList
    )

    @BeforeEach
    override fun setup() {
        super.setup()
        // Arrange
        coEvery { booksApiService.getNewBooks() } returns Response.success(createNewResponse(makeNewBooks()))
        val mainRepository: MainRepository = MainRepositoryImpl(booksApiService)
        sut = MainViewModel(mainRepository)
    }

    @Test
    fun `새 책 리스트를 가져올 때 성공적으로 응답을 가져옵니다`() = runTest {
        // Act
        val testLiveData = sut.stateLiveData
        val observer = testLiveData.test()
        sut.fetchData()
        val bookList = makeNewBooks()

        // Assert
        observer.assertValueSequence(
            listOf(
                MainViewState.Unitialized,
                MainViewState.Loading,
                MainViewState.Success(bookList),
            ),
            true
        )

    }

    @ParameterizedTest
    @MethodSource("parameters")
    fun `1 + 1 = 2`(a: Int, b: Int, c: Int) {
        assert(a + b == c)
    }

    companion object {

        @JvmStatic
        fun parameters() = Stream.of(
            Arguments.of(1, 2, 3),
            Arguments.of(10, 20, 30),
        )

    }

}
