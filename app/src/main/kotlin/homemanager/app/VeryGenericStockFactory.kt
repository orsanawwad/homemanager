package homemanager.app

class VeryGenericStockFactory: AbstractStockFactory() {
    override fun CreateStock(product: IProduct, quantity: Int, expire: Long): IStock {
        return VeryGenericStock(product, quantity, expire)
    }
}