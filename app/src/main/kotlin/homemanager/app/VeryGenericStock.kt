package homemanager.app

import java.util.*

class VeryGenericStock(
        private val product: IProduct,
        private val quantity: Int,
        private val expire: Long
): IStock {
    private val addedTime = System.currentTimeMillis()

    private val id = UUID.randomUUID().toString()

    override fun GetID(): String {
        return this.id
    }

    override fun GetQuantity(): Int {
        return quantity
    }

    override fun GetProduct(): IProduct {
        return product
    }

    override fun GetAddedTimestamp(): Long {
        return addedTime
    }

    override fun GetExpireTimestamp(): Long {
        return this.expire
    }


}