package io.pantheon.flare

import com.google.firebase.analytics.FirebaseAnalytics
import java.lang.Error

class FirebaseAnalyticsAdapter: AnalyticsAdapter<FirebaseAnalytics>() {

    override fun initialize(block: FirebaseAnalytics?.() -> Unit) {
        //            block(FirebaseAnalytics.getInstance(null))
    }

}