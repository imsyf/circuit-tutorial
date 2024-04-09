package im.syf.circuitcounter.feature.counter

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object CounterScreen : Screen {
    data class State(
        val count: Int,
        val eventSink: (Event) -> Unit = {}
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data object Increment : Event
        data object Decrement : Event
    }
}

class CounterPresenter : Presenter<CounterScreen.State> {

    @Composable
    override fun present(): CounterScreen.State {
        var count by rememberRetained { mutableIntStateOf(0) }

        return CounterScreen.State(count = count) { event ->
            when (event) {
                CounterScreen.Event.Decrement -> count--
                CounterScreen.Event.Increment -> count++
            }
        }
    }
}

@Composable
fun Counter(
    state: CounterScreen.State,
    modifier: Modifier = Modifier,
) {
    val color = if (state.count < 0) MaterialTheme.colorScheme.error else Color.Unspecified

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.displayLarge,
                color = color,
                text = "Count: ${state.count}"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Button(
                    onClick = { state.eventSink(CounterScreen.Event.Increment) }
                ) {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Filled.Add),
                        contentDescription = "Increment"
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { state.eventSink(CounterScreen.Event.Decrement) }
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
private fun PositivePreview() {
    Counter(state = CounterScreen.State(count = 1))
}

@Preview
@Composable
private fun NegativePreview() {
    Counter(state = CounterScreen.State(count = -1))
}
