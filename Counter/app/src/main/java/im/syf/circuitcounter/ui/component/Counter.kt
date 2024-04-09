package im.syf.circuitcounter.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Counter(
    count: Int,
    modifier: Modifier = Modifier,
    onIncrement: () -> Unit = {},
    onDecrement: () -> Unit = {},
) {
    val color = if (count < 0) MaterialTheme.colorScheme.error else Color.Unspecified

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.displayLarge,
                color = color,
                text = "Count: $count"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Button(
                    onClick = onIncrement
                ) {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Filled.Add),
                        contentDescription = "Increment"
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = onDecrement
                ) {
                    Icon(
                        painter = rememberVectorPainter(Remove),
                        contentDescription = "Decrement"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    Counter(count = 1)
}
