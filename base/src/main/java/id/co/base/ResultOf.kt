package id.co.base

sealed class ResultOf<out T> {
    class Loading<out T> : ResultOf<T>()
    data class Success<out T>(val data: T) : ResultOf<T>()
    data class Failure<out T>(val exception: Throwable) : ResultOf<T>()
}