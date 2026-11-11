package sa.factory.naqdterminalposcas.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import sa.factory.naqdterminalposcas.model.NaqdTerminalItem

@Entity(tableName = "pos_cashier_items_cache")
data class NaqdTerminalEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val priceSar: Double,
    val badge: String,
    val category: String,
    val status: String
) {
    fun toDomain(): NaqdTerminalItem = NaqdTerminalItem(
        id = id,
        title = title,
        description = description,
        priceSar = priceSar,
        badge = badge,
        category = category,
        status = status
    )
}
