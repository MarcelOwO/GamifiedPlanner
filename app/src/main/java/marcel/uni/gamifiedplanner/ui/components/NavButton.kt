package marcel.uni.gamifiedplanner.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable

fun NavButton(label: String, route: String, nav: NavHostController) {

    val buttonColor = ButtonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        disabledContentColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
        disabledContainerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.12f)
    )
    Button(
        colors = buttonColor,
        modifier = Modifier.defaultMinSize(0.dp,0.dp),
        contentPadding= PaddingValues(0.dp),
        shape = RoundedCornerShape(10.dp),
        onClick = { nav.navigate(route) },
    ) {
        Text(text = label, modifier = Modifier.padding(1.dp))
    }
}