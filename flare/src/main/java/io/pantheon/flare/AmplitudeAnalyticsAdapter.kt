package io.pantheon.flare

import com.amplitude.api.Amplitude
import com.amplitude.api.AmplitudeClient
import java.lang.Error

class AmplitudeAnalyticsAdapter: AnalyticsAdapter<AmplitudeClient>() {

    override fun initialize(block: AmplitudeClient?.() -> Unit) {
        if(isAmplitudeAvailable()) {
            block(
                Amplitude.getInstance()
                .initialize(null, null, null))
        } else throw Error()
    }

    private fun isAmplitudeAvailable(): Boolean {
        return try {
            Class.forName("com.amplitude.api.Amplitude")
            true
        } catch (t: Throwable) {
            false
        }
    }
}