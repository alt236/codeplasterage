package uk.co.alt236.codeplasterage.log

import com.google.common.reflect.ClassPath.ClassInfo
import java.lang.reflect.Executable
import kotlin.reflect.KClass

@Suppress("unused")
internal object Log {
    private const val LABEL_EXCEPTION = "Exception: "
    private const val LABEL_CAUSE = "    cause: "
    private const val LABEL_ON = "       on: "
    private const val LABEL_WHEN = "    while: "

    fun log(where: String, what: Any) {
        println("[$where] $what")
    }

    fun logE(throwable: Throwable, executable: Executable, context: String? = null) {
        logError(throwable, executable.toString(), context)
    }

    fun logE(throwable: Throwable, clazz: KClass<*>, context: String? = null) {
        logError(throwable, clazz.toString(), context)
    }
    fun logE(throwable: Throwable, clazz: Class<*>, context: String? = null) {
        logError(throwable, clazz.toString(), context)
    }

    fun logE(throwable: Throwable, classInfo: ClassInfo, context: String? = null) {
        logError(throwable, classInfo.toString(), context)
    }

    fun logE(message: String, throwable: Throwable) {
        System.err.println(message)
        throwable.printStackTrace()
    }

    private fun logError(throwable: Throwable, onWhat: String, context: String?) {
        val message = buildString {
            append(LABEL_EXCEPTION)
            append("${throwable::class.java.simpleName}(${throwable.message ?: ""})")
            if (throwable.cause != null) {
                append("\n")
                append(LABEL_CAUSE)
                append(throwable.cause)
            }

            append("\n")
            append(LABEL_ON)
            append(onWhat)

            if (!context.isNullOrEmpty()) {
                append("\n")
                append(LABEL_WHEN)
                append(context)
            }
        }

        System.err.println(message)
    }
}
