package auboris.strategy.auborispulse.ui.composable.screen.checkout

import androidx.compose.runtime.Composable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import auboris.strategy.auborispulse.data.entity.BookingEntity

@Composable
fun CheckoutDialog(
    booking: BookingEntity,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onConfirm,
        title = { Text("Consultation confirmed") },
        text = {
            Text(
                "Session ${booking.bookingNumber} is reserved for ${booking.customerFirstName} ${booking.customerLastName}. " +
                    "Your consultant will be waiting in the online conference or at the Auboris office at the appointed time.",
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("View bookings")
            }
        },
    )
}
