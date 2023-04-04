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
package io.pantheon.flare

import AnalyticsPayload
import io.pantheon.flare.adapters.AnalyticsAdapter

class Flare {

    class Builder {
        fun addAdapter(block: () -> AnalyticsAdapter<*>): Builder {
            ADAPTERS.add(block())
            return this
        }

        fun build(props: List<String>) {
            PROPERTIES.clear()
            PROPERTIES.addAll(props)
            generateEventPropertyMappings(PROPERTIES)
        }
    }

    companion object {
        private val ADAPTERS = mutableListOf<AnalyticsAdapter<*>>()
        private val PROPERTIES = mutableListOf<String>()

        fun trackAll(block: AnalyticsPayload.() -> Unit) {
            AnalyticsPayload()
                .apply(block)
                .also { ADAPTERS.forEach { it.logEvent("", ) } }
        }
    }
}
