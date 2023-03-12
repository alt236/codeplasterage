package uk.co.alt236.codeplasterage.reflection.classfinder

import com.google.common.reflect.ClassPath
import uk.co.alt236.codeplasterage.config.TesterConfig
import uk.co.alt236.codeplasterage.log.Log
import uk.co.alt236.codeplasterage.reflection.classfinder.filters.ConfigDrivenClassFilter
import uk.co.alt236.codeplasterage.reflection.classfinder.filters.SystemClassFilter

class ClassFinder(
    private val debug: Boolean = false,
    private val systemFilter: ClassFinderFilter = SystemClassFilter()
) {

    @Synchronized
    fun getClasses(config: TesterConfig): List<Class<*>> {
        val filter = ConfigDrivenClassFilter(config)
        val result = ArrayList<Class<*>>()
        printDebug("Searching for matching classes")

        val loader = Thread.currentThread().contextClassLoader

        for (info in ClassPath.from(loader).allClasses) {
            if (filter.isIncluded(info.name)) {
                val clazz = info.tryToLoad()
                if (clazz != null) {
                    if (systemFilter.isIncluded(clazz)) {
                        printDebug("Including: $clazz.")
                        result.add(clazz)
                    } else {
                        printDebug("Excluding: $clazz")
                    }
                }
            }
        }

        printDebug("Returning '${result.size}' classes")

        if (result.isEmpty()) {
            print("No classes found that match passed filters!")
        }

        return result
    }

    private fun ClassPath.ClassInfo.tryToLoad(): Class<*>? {
        return try {
            this.load()
        } catch (e: NoClassDefFoundError) {
            return null
        }
    }

    private fun printDebug(message: String) {
        if (debug) {
            Log.log("ClassFinder", message)
        }
    }
}
