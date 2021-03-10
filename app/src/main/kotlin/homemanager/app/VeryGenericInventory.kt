package homemanager.app

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class VeryGenericInventory: IInventory {

    val uuid = UUID.randomUUID().toString()

    // Product storage could be implemented in different ways with indexing and such, which should demonstrate the expandibility here
//    val products = LinkedList<IProduct>()
//    val products = HashSet<IProduct>()
    val products = HashMap<String, IProduct>()

    val stocks = HashMap<String, IStock>()

//    val stocks = LinkedList<IStock>()

    override fun GetID(): String {
        return uuid
    }

    override fun AddStock(stock: IStock) {
        if (products[stock.GetProduct().GetBarcode()] != null) {
            if (stocks[stock.GetID()] == null) {
                // TODO: Add check if its a negative number
                stocks[stock.GetID()] = stock
            } else {
                println("Error, stock with the same ID already exists")
            }
        } else {
            println("Error, inventory does not contain product")
        }
    }

    override fun GetStocksForProduct(product: IProduct): List<IStock> {
        val result = LinkedList<IStock>()
        for (stock in stocks.values) {
            if (stock.GetProduct().GetBarcode() == product.GetBarcode()) {
                result.add(stock)
            }
        }
        return result
    }

    override fun UpdateStock(stock: IStock) {
        if (stocks[stock.GetID()] != null) {
            stocks[stock.GetID()] = stock
        }
    }

    override fun AddProduct(product: IProduct) {
//        products.add(product)
        //TODO: product should be stored by barcode, not by id, barcodes must be unique
        if (products[product.GetBarcode()] == null) {
            products[product.GetBarcode()] = product
        } else {
            println("Product with the same barcode exists")
        }
    }

    // O(N) search, could be optimized even further, not ideal for auto completion
    override fun SearchProduct(searchText: String): List<IProduct> {
        val result = LinkedList<IProduct>()
        for (product in products.values) {
            if (product.GetName().contains(searchText)) {
                result.add(product)
            }
        }
        return result
    }

    override fun GetAllProducts(): List<IProduct> {
        return products.values.toList()
    }
}