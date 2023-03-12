package uk.co.alt236.codeplasterage.testers.common

import uk.co.alt236.codeplasterage.config.TesterConfig
import uk.co.alt236.codeplasterage.log.Log

internal class TestLogger(private val config: TesterConfig, private val tag: String) {

    fun log(message: Any) {
        if (config.debug) {
            Log.log("TESTER-$tag", message)
        }
    }
}
