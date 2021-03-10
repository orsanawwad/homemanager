package homemanager.app

abstract class AbstractStockFactory {
    abstract fun CreateStock(product: IProduct, quantity: Int, expire: Long): IStock
}