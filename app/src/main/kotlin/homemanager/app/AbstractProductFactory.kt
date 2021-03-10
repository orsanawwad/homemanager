package homemanager.app

abstract class AbstractProductFactory {
    //Might need to use a different design pattern?
    abstract fun CreateProduct(barcode: String, name: String, price: String): IProduct
}