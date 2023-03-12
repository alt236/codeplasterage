package uk.co.alt236.codeplasterage.testers.reflection

import org.junit.runner.notification.RunNotifier
import uk.co.alt236.codeplasterage.config.TesterConfig
import uk.co.alt236.codeplasterage.testers.common.TestLogger
import uk.co.alt236.codeplasterage.testers.common.TestNotifier
import uk.co.alt236.codeplasterage.testers.common.Tester
import java.lang.reflect.Method

internal abstract class BaseReflectiveTester(protected val config: TesterConfig) : Tester {
    protected val logger = TestLogger(config, this::class.java.simpleName)

    fun test(
        classes: Collection<Class<*>>,
        notifier: RunNotifier,
        method: Method
    ) {
        logger.log("####### Starting Tester! #######'")
        testInternal(classes, TestNotifier(config, logger, notifier), method)
    }

    protected abstract fun testInternal(
        classes: Collection<Class<*>>,
        notifier: TestNotifier,
        method: Method
    )

    protected fun getDescriptionText(
        index: Int,
        count: Int,
        method: Method,
        currentClass: Class<*>,
        additionalData: Any? = null
    ): String {
        val suffix = if (additionalData == null) {
            currentClass.name
        } else {
            "${currentClass.name}$additionalData"
        }
        return "${method.name} [${index + 1}/$count]: $suffix"
    }
}
