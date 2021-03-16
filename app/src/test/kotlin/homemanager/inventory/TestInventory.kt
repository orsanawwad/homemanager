package homemanager.inventory

import org.junit.jupiter.api.Test

class TestInventory {

    @Test
    fun TestSearchStrategy() {
        val inv = InMemoryInventoryRepository()
        val inventoryRepository: IInventoryRepository = inv
        val inventorySearch: IInventorySearchStrategy = inv

        inventorySearch.SearchProductStrategy("Test",SearchStrategyFactory().ReturnAllPrducts())
        TODO("WIP")
    }
}