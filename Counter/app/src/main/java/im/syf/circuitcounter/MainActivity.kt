package im.syf.circuitcounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitContent
import im.syf.circuitcounter.feature.counter.Counter
import im.syf.circuitcounter.feature.counter.CounterPresenter
import im.syf.circuitcounter.feature.counter.CounterScreen
import im.syf.circuitcounter.ui.theme.CounterTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CounterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CounterApp()
                }
            }
        }
    }
}

@Composable
private fun CounterApp() {
    val circuit = Circuit.Builder()
        .addPresenter<CounterScreen, CounterScreen.State>(CounterPresenter())
        .addUi<CounterScreen, CounterScreen.State> { state, modifier -> Counter(state, modifier) }
        .build()

    CircuitCompositionLocals(circuit = circuit) {
        CircuitContent(screen = CounterScreen)
    }
}
