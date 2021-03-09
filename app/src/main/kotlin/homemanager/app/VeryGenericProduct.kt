package homemanager.app

class VeryGenericProduct(
        private val barcode: String,
        private val name: String,
        private val price: String
): IProduct {
    override fun GetBarcode(): String {
        return barcode
    }

    override fun GetName(): String {
        return name
    }

    override fun GetPrice(): String {
        return price
    }
}