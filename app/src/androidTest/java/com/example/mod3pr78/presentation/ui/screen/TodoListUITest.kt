package com.example.mod3pr78.presentation.ui.screen
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.example.mod3pr78.domain.model.TodoItem
import org.junit.Rule
import org.junit.Test

class TodoListUITest {
    @get:Rule
    val rule = createComposeRule()
    private val testTodos = listOf(
        TodoItem(1, "Купить продукты", "2 литра, обезжиренное", false),
        TodoItem(2, "Позвонить маме", "Спросить про выходные", true),
        TodoItem(3, "Сделать ДЗ по Android", "Compose", false)
    )

    @Test
    fun all_three_tasks_are_displayed() {
        rule.setContent {
            TodoListScreen(
                viewModel = FakeTodoViewModel(testTodos),
                navController = rememberNavController()
            )
        }
        rule.onNodeWithText("Купить продукты").assertExists()
        rule.onNodeWithText("Позвонить маме").assertExists()
        rule.onNodeWithText("Сделать ДЗ по Android").assertExists()
    }
}
