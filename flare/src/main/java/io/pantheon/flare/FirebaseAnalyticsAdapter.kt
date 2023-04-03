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

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import org.json.JSONObject

class FirebaseAnalyticsAdapter : AnalyticsAdapter<FirebaseAnalytics>() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun initialize(block: FirebaseAnalytics?.() -> Unit): AnalyticsAdapter<FirebaseAnalytics> {
        FlareProvider.flareContext?.let { context: Context ->
            firebaseAnalytics = FirebaseAnalytics.getInstance(context)
            block(firebaseAnalytics)
        }
        return this
    }

    override fun logEvent(eventName: String, eventMap: HashMap<String?, Any?>) {
        require(::firebaseAnalytics.isInitialized) { Exception("Flare: FirebaseAnalytics not initialized") }
        firebaseAnalytics.logEvent(eventName, BundleJSONConverter.convertToBundle(JSONObject(eventMap)))
    }
}
