package homemanager.inventory

class SearchStrategyFactory() {
    fun SearchProductByName(keyword: String): ISearchStrategyPredicate<Product> {
        return ProductSearchByName(keyword)
    }

    fun ReturnAllPrducts(): ISearchStrategyPredicate<Product> {
        return ProductSearchReturnAllStrategy()
    }
}

class ProductSearchReturnAllStrategy: ISearchStrategyPredicate<Product> {
    override fun Search(t: Product): Boolean {
        return true
    }
}


class ProductSearchByName(private val keyword: String): ISearchStrategyPredicate<Product> {
    override fun Search(t: Product): Boolean {
        return (t.name.contains(keyword.trim()))
    }
}

interface ISearchStrategyPredicate<T> {
    fun Search(t: T): Boolean
}

interface IInventorySearchStrategy {
    fun SearchProductStrategy(userId: String, searchStrategy: ISearchStrategyPredicate<Product>): List<Product>?

    fun SearchStockStrategy(userId: String, searchStrategy: ISearchStrategyPredicate<Stock>): List<Stock>?

    fun SearchRecipeStrategy(userId: String, searchStrategy: ISearchStrategyPredicate<Recipe>): List<Recipe>?
}