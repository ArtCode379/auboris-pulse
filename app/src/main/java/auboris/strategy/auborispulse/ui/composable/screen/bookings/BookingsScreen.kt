package auboris.strategy.auborispulse.ui.composable.screen.bookings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import auboris.strategy.auborispulse.ui.composable.shared.ZVOPDContentWrapper
import auboris.strategy.auborispulse.ui.state.BookingUiState
import auboris.strategy.auborispulse.ui.state.DataUiState
import auboris.strategy.auborispulse.ui.theme.AuborisSuccess
import auboris.strategy.auborispulse.ui.viewmodel.BookingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookingsScreen(
    modifier: Modifier = Modifier,
    viewModel: BookingViewModel = koinViewModel(),
) {
    val bookingsState by viewModel.bookingsState.collectAsState()
    var bookingToCancel by remember { mutableStateOf<String?>(null) }
    BookingsContent(bookingsState, modifier) { bookingToCancel = it }
    bookingToCancel?.let { bookingNumber ->
        AlertDialog(
            onDismissRequest = { bookingToCancel = null },
            title = { Text("Cancel this booking?") },
            text = { Text("Your consultation slot will be released and cannot be restored automatically.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.cancelBooking(bookingNumber)
                        bookingToCancel = null
                    },
                ) {
                    Text("Cancel booking", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { bookingToCancel = null }) {
                    Text("Keep booking")
                }
            },
        )
    }
}

@Composable
private fun BookingsContent(
    bookingsState: DataUiState<List<BookingUiState>>,
    modifier: Modifier,
    onCancel: (String) -> Unit,
) {
    ZVOPDContentWrapper(
        dataState = bookingsState,
        dataPopulated = {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items((bookingsState as DataUiState.Populated).data, key = { it.bookingNumber }) { booking ->
                    BookingCard(booking, onCancel)
                }
            }
        },
        dataEmpty = {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("No bookings yet", style = MaterialTheme.typography.headlineSmall)
                Text("Browse Services from Home to plan your first strategic session.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        },
    )
}

@Composable
private fun BookingCard(booking: BookingUiState, onCancel: (String) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                Text(booking.serviceName, style = MaterialTheme.typography.titleMedium)
                Surface(color = AuborisSuccess.copy(alpha = 0.14f), shape = RoundedCornerShape(50)) {
                    Text("Confirmed", Modifier.padding(horizontal = 10.dp, vertical = 5.dp), color = AuborisSuccess)
                }
            }
            Text("Session ${booking.bookingNumber}", color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(booking.timestamp)
            TextButton(onClick = { onCancel(booking.bookingNumber) }) {
                Text("Cancel", color = Color(0xFFB3261E))
            }
        }
    }
}
