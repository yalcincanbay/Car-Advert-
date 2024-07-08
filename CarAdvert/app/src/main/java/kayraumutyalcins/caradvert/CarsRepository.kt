package kayraumutyalcins.caradvert

import kotlinx.coroutines.flow.Flow

interface CarsRepository {
    fun getAllCarsStream(): Flow<List<Car>>
    suspend fun insertCar(car: Car)
    suspend fun deleteCar(car: Car)
}

class OfflineCarsRepository(private val carDao: CarDao) : CarsRepository {
    override fun getAllCarsStream(): Flow<List<Car>> {
        return carDao.getAllCars()
    }

    override suspend fun insertCar(car: Car) {
        carDao.addCar(car)
    }

    override suspend fun deleteCar(car: Car) {
        carDao.deleteCar(car)
    }
}
