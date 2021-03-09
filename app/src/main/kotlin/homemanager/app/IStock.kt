package homemanager.app

interface IStock {
    fun GetQuantity(): Int //Double?, Measurement?
    fun GetProduct(): IProduct
    fun GetAddedTimestamp(): Long //Timestamp, from System.currentTimeMillis() on insertion
}