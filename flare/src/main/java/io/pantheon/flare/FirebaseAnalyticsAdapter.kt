package io.pantheon.flare

import com.google.firebase.analytics.FirebaseAnalytics
import java.lang.Error

class FirebaseAnalyticsAdapter: AnalyticsAdapter<FirebaseAnalytics>() {

    override fun initialize(block: FirebaseAnalytics?.() -> Unit) {
        if(isFirebaseAnalyticsAvailable()) {
            block(FirebaseAnalytics.getInstance(null))
        } else throw Error()
    }

    private fun isFirebaseAnalyticsAvailable(): Boolean {
        return try {
            Class.forName("com.google.firebase.analytics.FirebaseAnalytics")
            true
        } catch (t: Throwable) {
            false
        }
    }
}