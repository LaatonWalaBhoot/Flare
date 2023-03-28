package io.pantheon.flare

abstract class AnalyticsAdapter<T> {

    abstract fun initialize(block: T?.() -> Unit)
}