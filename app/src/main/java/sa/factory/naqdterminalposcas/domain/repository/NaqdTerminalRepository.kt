package sa.factory.naqdterminalposcas.domain.repository

import sa.factory.naqdterminalposcas.model.NaqdTerminalItem
import sa.factory.naqdterminalposcas.model.NaqdTerminalOrder
import sa.factory.naqdterminalposcas.model.NaqdTerminalTelemetry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface NaqdTerminalRepository {
    val catalogFlow: Flow<List<NaqdTerminalItem>>
    val activeCart: StateFlow<List<NaqdTerminalItem>>
    val telemetryFlow: Flow<NaqdTerminalTelemetry>
    
    fun addToCart(item: NaqdTerminalItem)
    fun removeFromCart(item: NaqdTerminalItem)
    fun clearCart()
    suspend fun checkout(paymentMethod: String): NaqdTerminalOrder
    suspend fun refreshCatalog()
}
