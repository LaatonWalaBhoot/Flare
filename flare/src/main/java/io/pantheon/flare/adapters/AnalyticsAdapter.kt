/**
 * Copyright 2023 LaatonWalaBhoot
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.pantheon.flare.adapters

import android.content.Context
import io.pantheon.flare.FlareContextProvider


abstract class AnalyticsAdapter<T> {

    private var analyticsClient: T? = null
    internal lateinit var context: Context

    init {
        FlareContextProvider.flareContext?.let { context = it } ?: run { throw Exception("Flare: Context is NULL, It should not be null") }
    }

    fun initialize(block: T?.() -> Unit): AnalyticsAdapter<T> {
        analyticsClient = analyticsClient ?: initClient()
        block(analyticsClient)
        return this
    }

    abstract fun initClient(): T?

    fun logEvent(eventName: String, eventMap: HashMap<String?, Any?>) {
        val client = analyticsClient ?: throw Exception("Flare: Analytics client not initialized")
        logEventImpl(eventName, eventMap, client)
    }

    protected abstract fun logEventImpl(eventName: String, eventMap: HashMap<String?, Any?>, client: T)
}

