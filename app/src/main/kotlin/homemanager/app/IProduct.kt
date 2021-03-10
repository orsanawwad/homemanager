package homemanager.app

// Enforce some kind of comparator?
interface IProduct {
    // TODO: Ditch ID in favour of barcodes, users must scan their products
//    fun GetID(): String
    fun GetBarcode(): String
    fun GetName(): String
    fun GetPrice(): String //Need to decide how to store prices and do conversions
    // TODO() fun GetMeasurement()
}