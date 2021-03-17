package homemanager.inventory

class SearchStrategyFactory() {
    fun SearchProductByName(keyword: String): ISearchStrategyPredicate<Product> {
        return ProductSearchByName(keyword)
    }

    fun ReturnAllPrducts(): ISearchStrategyPredicate<Product> {
        return ProductSearchReturnAllStrategy()
    }

    fun ReturnStocksForProduct(productId: String): ISearchStrategyPredicate<Stock> {
        return StockSearchReturnAllForProductStrategy(productId)
    }

    fun ReturnRecipeByName(keyword: String): ISearchStrategyPredicate<Recipe> {
        return RecipeSearchByNameStrategy(keyword)
    }

    fun ReturnAllRecipes(): ISearchStrategyPredicate<Recipe> {
        return RecipeReturnAllStrategy()
    }
}

class RecipeReturnAllStrategy(): ISearchStrategyPredicate<Recipe> {
    override fun Search(t: Recipe): Boolean {
        return true
    }
}

class RecipeSearchByNameStrategy(private val keyword: String): ISearchStrategyPredicate<Recipe> {
    override fun Search(t: Recipe): Boolean {
        return t.name.contains(this.keyword.trim())
    }

}

class StockSearchReturnAllForProductStrategy(private val productId: String): ISearchStrategyPredicate<Stock> {
    override fun Search(t: Stock): Boolean {
        return t.product.id == this.productId
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