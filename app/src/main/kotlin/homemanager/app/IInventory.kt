package homemanager.app

interface IInventory {

    fun GetId(): String

    // Add new stock, make sure to verify such product exists
    fun AddStock(stock: IStock)

    // Get Stocks for specific product
    fun GetStocks(product: IProduct): List<IStock>

    // TODO: Might need to make it more generic
    // Lookup field masks
    // https://developers.google.com/slides/how-tos/field-masks
    fun UpdateStockQuantity(stock :IStock, quantity: Int)

    // Add New Product
    fun AddProduct(product: IProduct)

    // Null returned nothing
    // 1 or more means there are results
    // Should be moved to something else?
    fun SearchProduct(searchText: String): List<IProduct>?

    // In real world scenario we would have pagination support
    fun GetAllProducts(): List<IProduct>

}