package homemanager.utils

interface ICriteria<T> {
    fun Search(searchList: List<T>): List<T>
}

interface ICriteriaCustomReturn<T,R> {
    fun Search(searchList: List<T>): R
}