package kayraumutyalcins.caradvert

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import androidx.compose.foundation.Image

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val cars = mainViewModel.cars.collectAsState(initial = listOf()).value
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Vehicle Sales List",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = modifier.weight(1f)) {
            items(cars) { car ->
                CarCard(car, onDeleteClick = { mainViewModel.deleteCar(it) }, navController)
            }
        }
        FloatingActionButton(
            onClick = { navController.navigate("APScreen") },
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add Car")
        }
    }
}

@Composable
fun CarCard(car: Car, onDeleteClick: (Car) -> Unit, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { navController.navigate("CarDetail/${car.id}") },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                car.imageUri?.let {
                    Image(
                        painter = rememberImagePainter(it),
                        contentDescription = null,
                        modifier = Modifier.size(64.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "${car.make} ${car.model} (${car.year})",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Text(text = "Distance: ${car.mileage} km")
                    Text(text = "Color: ${car.color}")
                    Text(text = "Price: \$${car.price}")
                }
            }
            Button(onClick = { onDeleteClick(car) }, modifier = Modifier.align(Alignment.End)) {
                Text("Delete")
            }
        }
    }
}
