package homemanager.app

class VeryGenericStock(
        private val product: IProduct,
        private val quantity: Int
): IStock {
    val addedTime = System.currentTimeMillis()
    override fun GetQuantity(): Int {
        return quantity
    }

    override fun GetProduct(): IProduct {
        return product
    }

    override fun GetAddedTimestamp(): Long {
        return addedTime
    }
}