package com.example.mod3pr78.presentation.ui.screen
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.example.mod3pr78.domain.model.TodoItem
import org.junit.Rule
import org.junit.Test

class TodoCheckboxTest {
    @get:Rule
    val rule = createComposeRule()
    private val testTodos = listOf(
        TodoItem(1, "Купить продукты", "2 литра, обезжиренное", false)
    )
    @Test
    fun checkbox_changes_state() {
        val fakeViewModel = FakeTodoViewModel(testTodos)
        rule.setContent {
            TodoListScreen(
                viewModel = fakeViewModel,
                navController = rememberNavController()
            )
        }
        val checkbox = rule.onNodeWithTag("todoCheckbox_1")
        checkbox.assertIsOff()
        checkbox.performClick()
        checkbox.assertIsOn()
    }
}
