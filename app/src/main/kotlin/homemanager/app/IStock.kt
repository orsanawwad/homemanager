package homemanager.app

interface IStock {
    fun GetID(): String
    fun GetQuantity(): Int //Double?, Measurement?
    fun GetProduct(): IProduct
    fun GetAddedTimestamp(): Long //Timestamp, from System.currentTimeMillis() on insertion
    fun GetExpireTimestamp(): Long
    //TODO: Figure out a way to store history
}