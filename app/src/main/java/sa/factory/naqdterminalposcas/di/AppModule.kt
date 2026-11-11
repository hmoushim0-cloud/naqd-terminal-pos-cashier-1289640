package sa.factory.naqdterminalposcas.di

import sa.factory.naqdterminalposcas.data.repository.NaqdTerminalRepositoryImpl
import sa.factory.naqdterminalposcas.domain.repository.NaqdTerminalRepository
import sa.factory.naqdterminalposcas.domain.usecase.*
import sa.factory.naqdterminalposcas.hardware.HardwareSensorDriver
import sa.factory.naqdterminalposcas.ui.viewmodel.NaqdTerminalViewModel

class DiContainer {
    val sensorDriver = HardwareSensorDriver()
    val repository: NaqdTerminalRepository = NaqdTerminalRepositoryImpl(sensorDriver)

    val getCatalogUseCase = GetCatalogUseCase(repository)
    val manageCartUseCase = ManageCartUseCase(repository)
    val processCheckoutUseCase = ProcessCheckoutUseCase(repository)
    val observeHardwareTelemetryUseCase = ObserveHardwareTelemetryUseCase(repository)

    fun provideViewModel(): NaqdTerminalViewModel = NaqdTerminalViewModel(
        getCatalogUseCase,
        manageCartUseCase,
        processCheckoutUseCase,
        observeHardwareTelemetryUseCase
    )
}
