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
import com.amplitude.api.Amplitude
import com.amplitude.api.AmplitudeClient
import org.json.JSONObject

class AmplitudeAnalyticsAdapter : AnalyticsAdapter<AmplitudeClient>() {

    private lateinit var amplitudeClient: AmplitudeClient

    override fun initialize(block: AmplitudeClient?.() -> Unit): AnalyticsAdapter<AmplitudeClient> {
        FlareProvider.flareContext?.let { context: Context ->
            Amplitude.getInstance().initialize(context, /* todo:API_KEY */ null, /* todo:USER-ID */null).apply { this?.let { client: AmplitudeClient -> amplitudeClient = client } }
            block(amplitudeClient)
        }
        return this
    }

    override fun logEvent(eventName: String, eventMap: HashMap<String?, Any?>) {
        require(::amplitudeClient.isInitialized) { Exception("Flare: AmplitudeClient not initialized") }
        amplitudeClient.logEvent(eventName, JSONObject(eventMap))
    }
}
