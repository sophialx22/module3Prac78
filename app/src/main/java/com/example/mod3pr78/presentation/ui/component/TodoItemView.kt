package com.example.mod3pr78.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.mod3pr78.domain.model.TodoItem

@Composable
fun TodoItemView(
    todo: TodoItem,
    onClick: () -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .clickable { onClick() }
        ) {
            Text(todo.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(todo.description, style = MaterialTheme.typography.bodyMedium)
        }

        Switch(
            checked = todo.isCompleted,
            onCheckedChange = { onCheckedChange(it) }
        )
    }
}
