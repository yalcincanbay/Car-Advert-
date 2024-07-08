package kayraumutyalcins.caradvert

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class Car(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val make: String,
    val model: String,
    val year: Int,
    val mileage: Int,
    val color: String,
    val price: Double,
    val description: String = "",
    val imageUri: String? = null,
)
