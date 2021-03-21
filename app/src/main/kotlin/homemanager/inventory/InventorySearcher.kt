package homemanager.inventory

import homemanager.utils.ICriteria

class SearchProductByNameCriteria(private val keyword: String): ICriteria<Product> {
    override fun Search(searchList: List<Product>): List<Product> {
        return searchList.filter { it.name.contains(keyword.trim()) }
    }
}

class ReturnStocksForProductCriteria(private val productId: String): ICriteria<Stock> {
    override fun Search(searchList: List<Stock>): List<Stock> {
        return searchList.filter { it.product.id == productId }
    }
}

class SearchRecipeByNameCriteria(private val keyword: String): ICriteria<Recipe> {
    override fun Search(searchList: List<Recipe>): List<Recipe> {
        return searchList.filter { it.name.contains(keyword.trim()) }
    }
}

class InventorySearchFactory() {
    fun SearchProductByName(keyword: String): ICriteria<Product> = SearchProductByNameCriteria(keyword)
    fun ReturnStocksForProduct(productId: String) = ReturnStocksForProductCriteria(productId)
    fun SearchRecipeByName(keyword: String) = SearchRecipeByNameCriteria(keyword)
}

// TODO: Move to abstract factory?
//class SearchFilterFactory() {
//    fun SearchProductByName(keyword: String): ISearchFilterPredicate<Product> {
//        return ProductSearchByName(keyword)
//    }
//
//    fun ReturnAllPrducts(): ISearchFilterPredicate<Product> {
//        return ProductSearchReturnAllFilter()
//    }
//
//    fun ReturnStocksForProduct(productId: String): ISearchFilterPredicate<Stock> {
//        return StockSearchReturnAllForProductFilter(productId)
//    }
//
//    fun ReturnRecipeByName(keyword: String): ISearchFilterPredicate<Recipe> {
//        return RecipeSearchByNameFilter(keyword)
//    }
//
//    fun ReturnAllRecipes(): ISearchFilterPredicate<Recipe> {
//        return RecipeReturnAllFilter()
//    }
//}
//
//class RecipeReturnAllFilter(): ISearchFilterPredicate<Recipe> {
//    override fun Search(t: Recipe): Boolean {
//        return true
//    }
//}
//
//class RecipeSearchByNameFilter(private val keyword: String): ISearchFilterPredicate<Recipe> {
//    override fun Search(t: Recipe): Boolean {
//        return t.name.contains(this.keyword.trim())
//    }
//
//}
//
//class StockSearchReturnAllForProductFilter(private val productId: String): ISearchFilterPredicate<Stock> {
//    override fun Search(t: Stock): Boolean {
//        return t.product.id == this.productId
//    }
//}
//
//class ProductSearchReturnAllFilter: ISearchFilterPredicate<Product> {
//    override fun Search(t: Product): Boolean {
//        return true
//    }
//}
//
//class ProductSearchByName(private val keyword: String): ISearchFilterPredicate<Product> {
//    override fun Search(t: Product): Boolean {
//        return (t.name.contains(keyword.trim()))
//    }
//}

//interface ISearchFilterPredicate<T> {
//    fun Search(t: T): Boolean
//}
// TODO: https://www.tutorialspoint.com/design_pattern/filter_pattern.htm
// Go over this again

//interface IInventorySearchFilter {
//    fun SearchProductFilter(userId: String, searchFilter: ISearchFilterPredicate<Product>): List<Product>?
//
//    fun SearchStockFilter(userId: String, searchFilter: ISearchFilterPredicate<Stock>): List<Stock>?
//
//    fun SearchRecipeFilter(userId: String, searchFilter: ISearchFilterPredicate<Recipe>): List<Recipe>?
//}