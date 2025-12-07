package com.example.mod3pr78.presentation.ui.screen
import androidx.compose.material3.Text
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mod3pr78.domain.model.TodoItem
import com.example.mod3pr78.presentation.viewmodel.ITodoViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.junit.Rule
import org.junit.Test

class FakeTodoViewModelForTest(todosList: List<TodoItem>) : ITodoViewModel {
    private val _todos = MutableStateFlow(todosList)
    override val todos: StateFlow<List<TodoItem>> = _todos

    override fun toggleTodo(id: Int) {
        _todos.value = _todos.value.map {
            if (it.id == id) it.copy(isCompleted = !it.isCompleted) else it
        }
    }
}
class NavigationTest {
    @get:Rule
    val rule = createComposeRule()

    private val testTodos = listOf(
        TodoItem(1, "Купить продукты", "2 литра, обезжиренное", false)
    )
    @Test
    fun navigate_list_to_detail_and_back() {
        val fakeViewModel = FakeTodoViewModel(testTodos)
        rule.setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "list") {
                composable("list") {
                    TodoListScreen(fakeViewModel, navController)
                }
                composable(
                    "detail/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.IntType })
                ) { backStackEntry ->
                    val id = backStackEntry.arguments?.getInt("id") ?: 0
                    Text(text = "Детали задачи $id")
                }
            }
        }
        rule.onNodeWithText("Купить продукты").performClick()
        rule.onNodeWithText("Детали задачи 1").assertExists()
    }
}
