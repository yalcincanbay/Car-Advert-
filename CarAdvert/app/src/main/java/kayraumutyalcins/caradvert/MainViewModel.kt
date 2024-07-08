package kayraumutyalcins.caradvert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: CarsRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    val cars = repository.getAllCarsStream()

    fun addCar(car: Car) = viewModelScope.launch(ioDispatcher) {
        repository.insertCar(car)
    }

    fun deleteCar(car: Car) = viewModelScope.launch(ioDispatcher) {
        repository.deleteCar(car)
    }
}
