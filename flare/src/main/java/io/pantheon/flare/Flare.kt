package io.pantheon.flare

import com.amplitude.api.Amplitude
import com.amplitude.api.AmplitudeClient
import com.clevertap.android.sdk.CleverTapAPI
import com.google.firebase.analytics.FirebaseAnalytics
import java.lang.Error


class Flare {

    class Builder {

        fun registerAmplitude(block: AmplitudeClient.() -> Unit): Builder {
            if(isAmplitudeAvailable()) {
                block(Amplitude.getInstance()
                    .initialize(null, null, null))
                return this
            }

            throw Error()
        }

        fun registerFirebaseAnalytics(block: FirebaseAnalytics.() -> Unit): Builder {
            if(isFirebaseAnalyticsAvailable()) {
                block(FirebaseAnalytics.getInstance(null))
                return this
            }

            throw Error()
        }

        fun registerClevertap(block: CleverTapAPI?.() -> Unit): Builder {
            if(isFirebaseAnalyticsAvailable()) {
                block(CleverTapAPI.getDefaultInstance(null))
                return this
            }

            throw Error()
        }
    }

    class Config {

    }

    companion object {
        fun builder(): Builder {
            return Builder()
        }

        private fun isAmplitudeAvailable(): Boolean {
            return try {
                Class.forName("com.amplitude.api.Amplitude")
                true
            } catch (t: Throwable) {
                false
            }
        }

        private fun isFirebaseAnalyticsAvailable(): Boolean {
            return try {
                Class.forName("com.google.firebase.analytics.FirebaseAnalytics")
                true
            } catch (t: Throwable) {
                false
            }
        }

        private fun isClevertapAvailable(): Boolean {
            return try {
                Class.forName("com.clevertap.android.sdk.CleverTapAPI")
                true
            } catch (t: Throwable) {
                false
            }
        }
    }

}