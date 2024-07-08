@file:OptIn(ExperimentalMaterial3Api::class)

package kayraumutyalcins.caradvert

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import coil.compose.rememberImagePainter

@Composable
fun CarsNavHost(
    mainViewModel: MainViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = "HS", modifier = modifier) {
        composable(route = "HS") {
            HomeScreen(mainViewModel, navController, Modifier.fillMaxSize())
        }

        composable(route = "APScreen") {
            APScreen(onAddButtonClick = { car ->
                mainViewModel.addCar(car)
                navController.navigate("HS")
            })
        }

        composable(route = "CarDetail/{carId}") { backStackEntry ->
            val carId = backStackEntry.arguments?.getString("carId")?.toInt()
            val car = mainViewModel.cars.collectAsState(initial = listOf()).value.firstOrNull { it.id == carId }
            car?.let {
                CarDetailScreen(car = it, navController = navController)
            }
        }
    }
}

@Composable
fun APScreen(
    onAddButtonClick: (Car) -> Unit = {},
    navController: NavController? = null,
    paddingBetween: Dp = 8.dp
) {
    var make by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var mileage by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.backgcar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().alpha(0.5f)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Add New Car",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                TextField(
                    value = make,
                    onValueChange = { make = it },
                    label = { Text("Brand") },
                    modifier = Modifier.weight(1f).padding(end = paddingBetween / 2)
                )
                TextField(
                    value = model,
                    onValueChange = { model = it },
                    label = { Text("Model") },
                    modifier = Modifier.weight(1f).padding(start = paddingBetween / 2)
                )
            }

            Spacer(modifier = Modifier.height(paddingBetween))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                TextField(
                    value = year,
                    onValueChange = { year = it },
                    label = { Text("Year") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f).padding(end = paddingBetween / 2)
                )
                TextField(
                    value = mileage,
                    onValueChange = { mileage = it },
                    label = { Text("Distance") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f).padding(start = paddingBetween / 2)
                )
            }

            Spacer(modifier = Modifier.height(paddingBetween))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                TextField(
                    value = color,
                    onValueChange = { color = it },
                    label = { Text("Color") },
                    modifier = Modifier.weight(1f).padding(end = paddingBetween / 2)
                )
                TextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Price") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f).padding(start = paddingBetween / 2)
                )
            }

            Spacer(modifier = Modifier.height(paddingBetween))

            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(105.dp)
            )

            Spacer(modifier = Modifier.height(paddingBetween))

            Button(onClick = { launcher.launch("image/*") }) {
                Text("Upload Your Car Image")
            }

            imageUri?.let {
                Image(
                    painter = rememberImagePainter(it),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp).padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(paddingBetween * 2))

            errorMessage?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            Button(
                onClick = {
                    val carYear = year.toIntOrNull()
                    val carMileage = mileage.toIntOrNull()
                    val carPrice = price.toDoubleOrNull()

                    if (make.isNotBlank() && model.isNotBlank() && carYear != null && carMileage != null && carPrice != null) {
                        onAddButtonClick(Car(make = make, model = model, year = carYear, mileage = carMileage, color = color, price = carPrice, description = description, imageUri = imageUri?.toString()))
                        navController?.navigate("HS")
                    } else {
                        errorMessage = "Please fill in all the blanks correctly."
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Your Car")
            }
        }
    }
}
