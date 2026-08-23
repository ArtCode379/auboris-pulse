package auboris.strategy.auborispulse.ui.composable.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoGraph
import androidx.compose.material.icons.rounded.Groups
import androidx.compose.material.icons.rounded.Insights
import androidx.compose.material.icons.rounded.Route
import androidx.compose.material.icons.rounded.SettingsSuggest
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import auboris.strategy.auborispulse.data.model.ServiceModel
import auboris.strategy.auborispulse.ui.composable.shared.ZVOPDContentWrapper
import auboris.strategy.auborispulse.ui.state.DataUiState
import auboris.strategy.auborispulse.ui.theme.AuborisAccent
import auboris.strategy.auborispulse.ui.theme.AuborisGradientEnd
import auboris.strategy.auborispulse.ui.theme.AuborisGradientStart
import auboris.strategy.auborispulse.ui.viewmodel.ServiceViewModel
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

private data class CategoryItem(val name: String, val icon: ImageVector)

private val categories = listOf(
    CategoryItem("Strategy", Icons.Rounded.Route),
    CategoryItem("Operations", Icons.Rounded.SettingsSuggest),
    CategoryItem("Leadership", Icons.Rounded.Groups),
    CategoryItem("Change", Icons.Rounded.AutoGraph),
    CategoryItem("Market", Icons.Rounded.Insights),
)

private val portfolio = listOf(
    "Operating model reset" to "31% faster decisions",
    "Process transformation" to "22% lower cycle time",
    "Leadership alignment" to "4.7/5 team confidence",
)

private val articles = listOf(
    "Leading through strategic change" to "Five practices that turn uncertainty into coordinated action.",
    "Designing decisions, not diagrams" to "How to make organisation design practical and accountable.",
    "Reading a shifting market" to "A framework for separating signals from noise.",
)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ServiceViewModel = koinViewModel(),
    onNavigateToServiceDetails: (serviceId: Int) -> Unit,
) {
    val servicesState by viewModel.servicesState.collectAsState()
    ZVOPDContentWrapper(
        dataState = servicesState,
        dataPopulated = {
            ServicesPopulated(
                services = (servicesState as DataUiState.Populated).data,
                modifier = modifier,
                onNavigateToServiceDetails = onNavigateToServiceDetails,
            )
        },
        dataEmpty = {
            Column(modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                Text("Our consulting services are being prepared.")
            }
        },
    )
}

@Composable
private fun ServicesPopulated(
    services: List<ServiceModel>,
    modifier: Modifier = Modifier,
    onNavigateToServiceDetails: (serviceId: Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Card(
                modifier = Modifier.clickable { onNavigateToServiceDetails(services.first().id) },
                shape = RoundedCornerShape(20.dp),
            ) {
                Column(
                    Modifier
                        .background(Brush.linearGradient(listOf(AuborisGradientStart, AuborisGradientEnd)))
                        .padding(20.dp),
                ) {
                    Text("NEXT AVAILABLE", color = AuborisAccent, style = MaterialTheme.typography.labelLarge)
                    Text("Strategy session · Tomorrow, 09:30", color = Color.White, style = MaterialTheme.typography.titleLarge)
                    Text("Reserve a focused 90-minute session with an Auboris advisor.", color = Color.White.copy(alpha = 0.82f))
                }
            }
        }
        item {
            SectionTitle("Explore by focus")
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(categories) { category ->
                    Card(
                        modifier = Modifier.width(112.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                    ) {
                        Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Icon(category.icon, null, tint = MaterialTheme.colorScheme.primary)
                            Text(category.name, style = MaterialTheme.typography.labelLarge)
                        }
                    }
                }
            }
        }
        item { SectionTitle("Consulting services") }
        items(services, key = { it.id }) { service ->
            ServiceCard(service) { onNavigateToServiceDetails(service.id) }
        }
        item {
            SectionTitle("Selected outcomes")
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(portfolio) { project ->
                    Card(modifier = Modifier.width(210.dp)) {
                        Column(Modifier.padding(16.dp)) {
                            Text(project.second, color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.titleLarge)
                            Text(project.first, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }
        }
        item {
            SectionTitle("Knowledge base")
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                articles.forEach { article ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Rounded.Insights, null, tint = MaterialTheme.colorScheme.primary)
                            Spacer(Modifier.width(12.dp))
                            Column {
                                Text(article.first, style = MaterialTheme.typography.titleMedium)
                                Text(article.second, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(title, Modifier.padding(bottom = 10.dp), style = MaterialTheme.typography.titleLarge)
}

@Composable
private fun ServiceCard(service: ServiceModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(2.dp),
    ) {
        Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = service.imageUrl,
                contentDescription = service.name,
                modifier = Modifier
                    .size(104.dp)
                    .clip(RoundedCornerShape(14.dp)),
                contentScale = ContentScale.Crop,
            )
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(service.category.uppercase(), color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.labelLarge)
                Text(service.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    service.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                    Text("From $${service.price.toInt()}", fontWeight = FontWeight.Bold)
                    Surface(color = AuborisAccent.copy(alpha = 0.18f), shape = RoundedCornerShape(50)) {
                        Text("Book now", Modifier.padding(horizontal = 9.dp, vertical = 5.dp))
                    }
                }
            }
        }
    }
}
