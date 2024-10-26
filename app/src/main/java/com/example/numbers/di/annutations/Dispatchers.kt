package com.example.numbers.di.annutations

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatchers: NumberFactDispatchers)

enum class NumberFactDispatchers {
    IO,
    MAIN,
    DEFAULT,
}