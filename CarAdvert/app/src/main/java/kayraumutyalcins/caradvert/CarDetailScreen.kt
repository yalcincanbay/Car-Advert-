package kayraumutyalcins.caradvert

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@Composable
fun CarDetailScreen(car: Car, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.sneakskins),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().alpha(0.4f)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(
                text = "${car.make} ${car.model} (${car.year})",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 26.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            car.imageUri?.let {
                Image(
                    painter = rememberImagePainter(it),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Distance: ${car.mileage} km",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp
            )
            Text(
                text = "Color: ${car.color}",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp
            )
            Text(
                text = "Price: \$${car.price}",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp
            )
            Text(
                text = "Description: ${car.description}",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text("Back to car list")
            }
        }
    }
}
