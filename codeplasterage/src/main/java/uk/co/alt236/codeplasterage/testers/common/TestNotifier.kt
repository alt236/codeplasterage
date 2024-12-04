package uk.co.alt236.codeplasterage.testers.common

import org.junit.runner.Description
import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunNotifier
import uk.co.alt236.codeplasterage.config.TesterConfig
import uk.co.alt236.codeplasterage.log.Log

internal class TestNotifier(
    private val config: TesterConfig,
    private val logger: TestLogger,
    private val notifier: RunNotifier,
) {

    fun fireTestStarted(description: Description) {
        logger.log("===>  Starting test: $description")
        notifier.fireTestStarted(description)
    }

    fun fireTestFinished(description: Description, result: Throwable?) {
        if (result == null) {
            logger.log("===   Test SUCCESS!: $description")
            notifier.fireTestFinished(description)
        } else {
            if (config.ignoreErrors) {
                logger.log("===   Test FAILED - BUT WE ARE IGNORING IT!: '$description'. exception=$result")

                Log.logE(
                    "\n*** There was an exception while executing this test, but errors are ignored! ***\n",
                    result
                )
                notifier.fireTestFinished(description)
            } else {
                logger.log("===   Test FAILED!: $description")
                notifier.fireTestFailure(Failure(description, result))
            }
        }
    }
}
