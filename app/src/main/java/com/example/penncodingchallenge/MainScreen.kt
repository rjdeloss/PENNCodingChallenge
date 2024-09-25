package com.example.penncodingchallenge


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        SearchBar(modifier.padding(16.dp))
        LocationInfoCard(modifier.padding(16.dp))
        AQITabs(modifier.padding(16.dp))
        ForecastCard(modifier.padding(16.dp))
    }

}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = { Text(text = "Search")},
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Composable
fun LocationInfoCard(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Longitude: 3123235341", fontSize = 24.sp)
        Text(text = "Latitude: 343242321", fontSize = 24.sp)
        Text(text = "City: Bangkok", fontSize = 24.sp)
        Text(text = "Weather Station: AG1", fontSize = 24.sp)
    }
}

@Composable
fun AQITabs(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "AQI Information", fontSize = 28.sp, fontWeight = FontWeight.Bold )
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Yesterday", fontSize = 20.sp)
                Text(text = "32", fontSize = 48.sp, fontWeight = FontWeight.Bold)
                Text(text = "Good", fontSize = 20.sp)
            }
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Today", fontSize = 20.sp)
                Text(text = "69", fontSize = 48.sp, fontWeight = FontWeight.Bold)
                Text(text = "Moderate", fontSize = 20.sp)
            }
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Tomorrow", fontSize = 20.sp)
                Text(text = "129", fontSize = 48.sp, fontWeight = FontWeight.Bold)
                Text(text = "Unhealthy", fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun ForecastCard(modifier: Modifier =Modifier) {
    Column(modifier = modifier) {
        Text(text = "Forecast", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text(text = "average: 154", fontSize = 24.sp)
        Text(text = "min: 131", fontSize = 24.sp)
        Text(text = "max: 157", fontSize = 24.sp)
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}