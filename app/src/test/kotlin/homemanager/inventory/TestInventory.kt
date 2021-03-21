package homemanager.inventory

import org.junit.jupiter.api.Test

class TestInventory {

    @Test
    fun TestSearchFilter() {
        val inv = InMemoryInventoryRepository()
        val inventoryRepository: IInventoryRepository = inv
        val inventorySearch: IInventorySearchFilter = inv

        inventorySearch.SearchProductFilter("Test",SearchFilterFactory().ReturnAllPrducts())
//        TODO("WIP")
    }
}