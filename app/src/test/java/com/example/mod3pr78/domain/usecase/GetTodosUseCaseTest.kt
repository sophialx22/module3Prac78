package com.example.mod3pr78
import com.example.mod3pr78.domain.usecase.GetTodosUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetTodosUseCaseTest {

    @Test
    fun `GetTodosUseCase returns 3 tasks`() = runBlocking {
        val repo = FakeRepository()
        val useCase = GetTodosUseCase(repo)

        val result = useCase()

        assertEquals(3, result.size)
    }
}
