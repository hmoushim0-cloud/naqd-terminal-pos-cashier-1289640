package sa.factory.naqdterminalposcas.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.naqdterminalposcas.domain.usecase.*
import sa.factory.naqdterminalposcas.model.NaqdTerminalItem
import sa.factory.naqdterminalposcas.model.NaqdTerminalOrder
import sa.factory.naqdterminalposcas.model.NaqdTerminalTelemetry
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class NaqdTerminalUiState(
    val catalog: List<NaqdTerminalItem> = emptyList(),
    val filteredCatalog: List<NaqdTerminalItem> = emptyList(),
    val selectedCategory: String = "الكل",
    val cart: List<NaqdTerminalItem> = emptyList(),
    val telemetry: NaqdTerminalTelemetry? = null,
    val lastOrder: NaqdTerminalOrder? = null,
    val isCheckingOut: Boolean = false,
    val activeTab: String = "home"
)

class NaqdTerminalViewModel(
    private val getCatalogUseCase: GetCatalogUseCase,
    private val manageCartUseCase: ManageCartUseCase,
    private val processCheckoutUseCase: ProcessCheckoutUseCase,
    private val observeHardwareTelemetryUseCase: ObserveHardwareTelemetryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NaqdTerminalUiState())
    val uiState: StateFlow<NaqdTerminalUiState> = _uiState.asStateFlow()

    init {
        // Observe Catalog
        getCatalogUseCase().onEach { list ->
            _uiState.update { it.copy(catalog = list, filteredCatalog = list) }
        }.launchIn(viewModelScope)

        // Observe Cart
        manageCartUseCase.cart.onEach { cartItems ->
            _uiState.update { it.copy(cart = cartItems) }
        }.launchIn(viewModelScope)

        // Observe Telemetry
        observeHardwareTelemetryUseCase().onEach { telem ->
            _uiState.update { it.copy(telemetry = telem) }
        }.launchIn(viewModelScope)
    }

    fun selectCategory(category: String) {
        _uiState.update { state ->
            val filtered = if (category == "الكل") state.catalog else state.catalog.filter { it.category == category }
            state.copy(selectedCategory = category, filteredCatalog = filtered)
        }
    }

    fun switchTab(tab: String) {
        _uiState.update { it.copy(activeTab = tab) }
    }

    fun addToCart(item: NaqdTerminalItem) = manageCartUseCase.add(item)
    fun removeFromCart(item: NaqdTerminalItem) = manageCartUseCase.remove(item)

    fun checkout() {
        viewModelScope.launch {
            _uiState.update { it.copy(isCheckingOut = true) }
            val order = processCheckoutUseCase()
            _uiState.update { it.copy(isCheckingOut = false, lastOrder = order, activeTab = "checkout") }
        }
    }
}
