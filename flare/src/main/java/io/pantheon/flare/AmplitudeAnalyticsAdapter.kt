package io.pantheon.flare

import com.amplitude.api.Amplitude
import com.amplitude.api.AmplitudeClient
import java.lang.Error

class AmplitudeAnalyticsAdapter: AnalyticsAdapter<AmplitudeClient>() {

    override fun initialize(block: AmplitudeClient?.() -> Unit) {
        block(
            Amplitude.getInstance()
                .initialize(null, null, null))
    }
}