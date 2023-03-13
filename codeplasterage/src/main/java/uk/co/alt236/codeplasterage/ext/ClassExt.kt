package uk.co.alt236.codeplasterage.ext

import uk.co.alt236.codeplasterage.log.Log

object ClassExt {


    fun Class<*>.canLoadMethods(): Boolean {
        try {
            this.methods
            return true
        } catch (e: Throwable) {
            Log.logE(e, this)
        }
        return false
    }

    fun Class<*>.implementsAnyOf(interfaces: Set<Class<*>>): Boolean {
        val set = this.getAllInterfaces()
        for (candidate in interfaces) {
            if (set.contains(candidate)) {
                return true
            }
        }
        return false
    }

    fun Class<*>.getAllInterfaces(): Set<Class<*>> {
        val result = HashSet<Class<*>>()
        getAllInterfaces(this, result)
        return result
    }

    private fun getAllInterfaces(clazz: Class<*>?, interfacesFound: MutableSet<Class<*>>) {
        var cls: Class<*>? = clazz
        while (cls != null) {
            val interfaces = cls.interfaces
            for (i in interfaces) {
                if (interfacesFound.add(i)) {
                    getAllInterfaces(i, interfacesFound)
                }
            }
            cls = cls.superclass
        }
    }
}
