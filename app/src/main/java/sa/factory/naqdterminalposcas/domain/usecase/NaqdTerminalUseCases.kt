package sa.factory.naqdterminalposcas.domain.usecase

import sa.factory.naqdterminalposcas.domain.repository.NaqdTerminalRepository
import sa.factory.naqdterminalposcas.model.NaqdTerminalItem
import sa.factory.naqdterminalposcas.model.NaqdTerminalOrder
import sa.factory.naqdterminalposcas.model.NaqdTerminalTelemetry
import kotlinx.coroutines.flow.Flow

class GetCatalogUseCase(private val repository: NaqdTerminalRepository) {
    operator fun invoke(): Flow<List<NaqdTerminalItem>> = repository.catalogFlow
}

class ManageCartUseCase(private val repository: NaqdTerminalRepository) {
    val cart = repository.activeCart
    fun add(item: NaqdTerminalItem) = repository.addToCart(item)
    fun remove(item: NaqdTerminalItem) = repository.removeFromCart(item)
    fun clear() = repository.clearCart()
}

class ProcessCheckoutUseCase(private val repository: NaqdTerminalRepository) {
    suspend operator fun invoke(paymentMethod: String = "Mada Contactless EMV"): NaqdTerminalOrder =
        repository.checkout(paymentMethod)
}

class ObserveHardwareTelemetryUseCase(private val repository: NaqdTerminalRepository) {
    operator fun invoke(): Flow<NaqdTerminalTelemetry> = repository.telemetryFlow
}
