package homemanager.app

class VeryGenericIngredient(
        private val product: IProduct,
        private val amount: Int
): IIngredient {
    override fun GetProduct(): IProduct {
        return product
    }

    override fun GetAmount(): Int {
        return amount
    }
}