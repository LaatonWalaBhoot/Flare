package io.pantheon.flare

import com.clevertap.android.sdk.CleverTapAPI
import java.lang.Error

class CleverTapAnalyticsAdapter: AnalyticsAdapter<CleverTapAPI>() {

    override fun initialize(block: CleverTapAPI?.() -> Unit) {
        block(CleverTapAPI.getDefaultInstance(null))
    }
}