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

import com.clevertap.android.sdk.CleverTapAPI

class CleverTapAnalyticsAdapter : AnalyticsAdapter<CleverTapAPI>() {

    private lateinit var cleverTap: CleverTapAPI

    override fun initialize(block: CleverTapAPI?.() -> Unit): AnalyticsAdapter<CleverTapAPI> {
        CleverTapAPI.getDefaultInstance(context).apply { this?.let { cleverTapAPI: CleverTapAPI -> cleverTap = cleverTapAPI } }
        block(cleverTap)
        return this
    }

    override fun logEvent(eventName: String, eventMap: HashMap<String?, Any?>) {
        require(::cleverTap.isInitialized) { Exception("Flare: CleverTapAPI not initialized") }
        cleverTap.pushEvent(eventName, eventMap)
    }
}
