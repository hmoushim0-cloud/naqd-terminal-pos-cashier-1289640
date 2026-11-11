package sa.factory.naqdterminalposcas.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NaqdTerminalDao {
    @Query("SELECT * FROM pos_cashier_items_cache ORDER BY id ASC")
    fun observeAllItems(): Flow<List<NaqdTerminalEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<NaqdTerminalEntity>)

    @Query("DELETE FROM pos_cashier_items_cache")
    suspend fun clearAll()
}
