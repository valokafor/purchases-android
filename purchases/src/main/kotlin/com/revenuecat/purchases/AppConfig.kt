package com.revenuecat.purchases

import android.content.Context
import java.net.URL

internal class AppConfig(
    context: Context,
    observerMode: Boolean,
    val platformInfo: PlatformInfo,
    proxyURL: URL?
) {

    val languageTag: String = context.getLocale()?.toBCP47() ?: ""
    val versionName: String = context.versionName ?: ""
    var finishTransactions: Boolean = !observerMode
    val baseURL: URL = proxyURL?.also {
        debugLog("Purchases is being configured using a proxy for RevenueCat")
    } ?: URL("https://api.revenuecat.com/")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AppConfig

        if (platformInfo != other.platformInfo) return false
        if (languageTag != other.languageTag) return false
        if (versionName != other.versionName) return false
        if (finishTransactions != other.finishTransactions) return false
        if (baseURL != other.baseURL) return false

        return true
    }

    override fun hashCode(): Int {
        var result = platformInfo.hashCode()
        result = 31 * result + languageTag.hashCode()
        result = 31 * result + versionName.hashCode()
        result = 31 * result + finishTransactions.hashCode()
        result = 31 * result + baseURL.hashCode()
        return result
    }

    override fun toString(): String {
        return "AppConfig(" +
            "platformInfo=$platformInfo, " +
            "languageTag='$languageTag', " +
            "versionName='$versionName', " +
            "finishTransactions=$finishTransactions, " +
            "baseURL=$baseURL)"
    }
}
