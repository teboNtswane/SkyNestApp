package com.example.skynestapplication

import android.net.Uri
import android.util.Log
import java.net.MalformedURLException
import java.net.URL

private val BIRDBASE_URL = "https://api.ebird.org/v2/data/obs/regions/recent?r=ZA"
private val PARAM_METRIC = "metric"
private val METRIC_VALUE = "true"
private val BPARAM_API_KEY = "key"
private val LOGGING_TAG = "URLWECREATED"

//Bird App
fun buildURLForBird(): URL? {
    val buildUri: Uri = Uri.parse(BIRDBASE_URL).buildUpon()
        .appendQueryParameter(
            BPARAM_API_KEY,
            "h0ns4ma0jkuo"
        )
        // passing in api key
        .appendQueryParameter(
            PARAM_METRIC,
            METRIC_VALUE
        ) // passing in metric as measurement unit
        .build()
    var url: URL? = null
    try {
        url = URL(buildUri.toString())
    } catch (e: MalformedURLException) {
        e.printStackTrace()
    }
    Log.i(LOGGING_TAG, "buildURLForBirdApp: $url")
    return url
}