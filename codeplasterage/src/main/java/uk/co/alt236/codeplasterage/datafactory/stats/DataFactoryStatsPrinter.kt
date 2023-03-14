package uk.co.alt236.codeplasterage.datafactory.stats

import uk.co.alt236.codeplasterage.datafactory.DummyDataFactory
import uk.co.alt236.codeplasterage.log.Log

internal class DataFactoryStatsPrinter {

    fun print(recorder: DataFactoryRequestRecorder) {
        val sb = StringBuilder()

        sb.append("------------------- \n")
        sb.addSection("Successful Requests", recorder.getSuccessful())
        sb.addSection("Unsatisfied Requests", recorder.getFailed())
        sb.append("------------------- \n")

        Log.logE(sb.toString())
    }

    private fun StringBuilder.addSection(section: String, classes: Map<Class<*>, Long>): StringBuilder {
        if (classes.isNotEmpty()) {
            val sortedKeys = classes.entries.sortedByDescending { it.value }.map { it.key }
            this.append("* ${DummyDataFactory::class.java.simpleName} $section: \n")

            for ((index, clazz) in sortedKeys.withIndex()) {
                val count = classes[clazz]
                val type = clazz.getType()
                val name = clazz.name
                val message = "  %5d: %5d %10s '%s'\n".format(index + 1, count, type, name)
                this.append(message)
            }
        } else {
            this.append("* None!\n")
        }

        return this
    }

    private fun Class<*>.getType(): String {
        return when {
            this.isInterface -> "interface"
            this.isEnum -> "enum"
            else -> "class"
        }
    }

}