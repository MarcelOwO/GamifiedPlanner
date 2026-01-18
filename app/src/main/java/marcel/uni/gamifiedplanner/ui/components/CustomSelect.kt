package marcel.uni.gamifiedplanner.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomSelect(options: List<String>, selected: String, onSelect: (String) -> Unit) {
    val selectedButtonColors = ButtonColors(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        disabledContentColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.12f),
        disabledContainerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.12f)
    )
    val unSelctedButtonColors = ButtonColors(
        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
        contentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
        disabledContentColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.12f),
        disabledContainerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.12f)
    )

    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.tertiary,
        modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp, horizontal = 5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            options.forEach { option ->
                Button(
                    modifier = Modifier.defaultMinSize(0.dp, 0.dp),
                    contentPadding = PaddingValues(vertical =4.dp,horizontal=8.dp),
                    colors = if (option == selected) selectedButtonColors else unSelctedButtonColors,
                    onClick = { onSelect(option) },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(option, color = MaterialTheme.colorScheme.secondary)
                }
            }
        }
    }
}