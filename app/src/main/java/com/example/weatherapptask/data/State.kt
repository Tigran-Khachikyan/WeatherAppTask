package com.example.weatherapptask.data

sealed class State<T> {

    class Loading<T> : State<T>()

    sealed class Success<T>(open val data: T) : State<T>() {
        class FromNetwork<T>(override val data: T) : Success<T>(data)
        class FromCache<T>(override val data: T) : Success<T>(data)
    }

    data class Error<T>(val reason: String) : State<T>()
}