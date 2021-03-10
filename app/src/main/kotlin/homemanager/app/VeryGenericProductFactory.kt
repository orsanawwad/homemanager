package homemanager.app

class VeryGenericProductFactory: AbstractProductFactory() {
    override fun CreateProduct(barcode: String, name: String, price: String): IProduct {
        return VeryGenericProduct(barcode, name, price)
    }
}