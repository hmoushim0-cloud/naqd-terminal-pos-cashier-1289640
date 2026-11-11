package sa.factory.naqdterminalposcas.data.remote

import sa.factory.naqdterminalposcas.model.NaqdTerminalItem
import sa.factory.naqdterminalposcas.model.NaqdTerminalOrder

interface NaqdTerminalApiService {
    suspend fun fetchCatalog(): List<NaqdTerminalItem>
    suspend fun submitOrder(order: NaqdTerminalOrder): Boolean
}
