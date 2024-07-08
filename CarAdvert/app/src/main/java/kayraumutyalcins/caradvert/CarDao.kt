package kayraumutyalcins.caradvert

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {
    @Query("SELECT * from cars ORDER BY make ASC, model ASC")
    fun getAllCars(): Flow<List<Car>>

    @Insert
    fun addCar(car: Car)

    @Delete
    fun deleteCar(car: Car)
}
