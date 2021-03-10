package homemanager.app

interface IIngredient {
    fun GetProduct(): IProduct
    fun GetAmount(): Int //TODO: Change to long or more universal measurement?
}