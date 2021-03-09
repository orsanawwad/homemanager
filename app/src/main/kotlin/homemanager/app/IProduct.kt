package homemanager.app

interface IProduct {
    fun GetBarcode(): String
    fun GetName(): String
    fun GetPrice(): String //Need to decide how to store prices and do conversions
    // TODO() fun GetMeasurement()
}