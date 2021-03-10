package homemanager.app

import java.time.LocalDateTime

// Facade for enduser
class Client: IInventoryServiceSetter {

    private var stockFactory: AbstractStockFactory? = null
    private var productFactory: AbstractProductFactory? = null
    private var inventoryService: IInventoryService? = null

    //TODO: temporarily
    var user: IUser? = null

    override fun SetInventoryService(inventoryService: IInventoryService) {
        this.inventoryService = inventoryService
    }

    // TODO: Consider creating a service for user
    fun SetCurrentUser(user: IUser) {
        this.user = user
    }

    fun InvokeStuff() {
        val inventory = this.user?.let { inventoryService?.CreateInventory(it) }
        val product = productFactory?.CreateProduct("123123","Tnova Milk 1Liter","16")
        product?.let { inventory?.AddProduct(it) }
        var stock = product?.let { stockFactory?.CreateStock(it,2, System.currentTimeMillis() + 1000*60*60*24*14) }
        stock?.let { inventory?.AddStock(it) }

        stock = product?.let { stockFactory?.CreateStock(it,5, System.currentTimeMillis() + 1000*60*60*24*13) }
        stock?.let { inventory?.AddStock(it) }

        stock = product?.let { stockFactory?.CreateStock(it,3, System.currentTimeMillis() + 1000*60*60*24*11) }
        stock?.let { inventory?.AddStock(it) }

        val products = inventory?.GetAllProducts()
        val stocks = product?.let { inventory?.GetStocksForProduct(it) }

        if (products != null) {
            for (pr in products) {
                println("${pr.GetBarcode()} ${pr.GetName()} ${pr.GetPrice()}")
            }
        }

        if (stocks != null) {
            for (st in stocks) {
                println("${st.GetID()} ${st.GetProduct().GetBarcode()} ${st.GetQuantity()} ${st.GetAddedTimestamp()} ${st.GetExpireTimestamp()}")
            }
        }

//        productFactory?.CreateProduct()?.let { inventory?.AddProduct(it) }
//        inventory.AddStock(stockFactory.Cre)


    }

    fun SetCurrentProductFactory(productFactory: AbstractProductFactory) {
        this.productFactory = productFactory
    }

    fun SetStockFactory(stockFactory: AbstractStockFactory) {
        this.stockFactory = stockFactory
    }

}