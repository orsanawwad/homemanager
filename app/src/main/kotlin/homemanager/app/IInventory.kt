package homemanager.app

//TODO: Discussion, should inventory actually use all these methods? or should an outside class manage an inventory
// and the inventory should only store Products and Stocks?
interface IInventory {

    fun GetID(): String

    // Add new stock, make sure to verify such product exists
    fun AddStock(stock: IStock)

    // Get Stocks for specific product
    fun GetStocksForProduct(product: IProduct): List<IStock>

    // TODO: Might need to make it more generic
    // Lookup field masks
    // https://developers.google.com/slides/how-tos/field-masks
    fun UpdateStock(stock :IStock)

    // Add New Product
    fun AddProduct(product: IProduct)

    // Null returned nothing
    // 1 or more means there are results
    // Should be moved to something else?
    fun SearchProduct(searchText: String): List<IProduct>

    // In real world scenario we would have pagination support
    fun GetAllProducts(): List<IProduct>

    //TODO: Add product update
    //TODO: Add product delete
    //TODO: Add stock deletion? or keep for analytics?
}