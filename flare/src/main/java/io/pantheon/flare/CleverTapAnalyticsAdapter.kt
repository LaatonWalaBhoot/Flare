package io.pantheon.flare

import com.clevertap.android.sdk.CleverTapAPI
import java.lang.Error

class CleverTapAnalyticsAdapter: AnalyticsAdapter<CleverTapAPI>() {

    override fun initialize(block: CleverTapAPI?.() -> Unit) {
        if(isClevertapAvailable()) {
            block(CleverTapAPI.getDefaultInstance(null))
        } else throw Error()
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