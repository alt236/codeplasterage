package dev.alt236.codeplasterage.datafactory.stats

class DataFactoryRequestRecorder {
    private val failed = HashMap<Class<*>, Long>()
    private val successful = HashMap<Class<*>, Long>()

    @Synchronized
    fun recordFailure(clazz: Class<*>) = failed.add(clazz)

    @Synchronized
    fun getFailed(): Map<Class<*>, Long> = failed.toMutableMap()

    @Synchronized
    fun recordSuccess(clazz: Class<*>) = successful.add(clazz)

    @Synchronized
    fun getSuccessful(): Map<Class<*>, Long> = successful.toMutableMap()

    @Synchronized
    private fun HashMap<Class<*>, Long>.add(clazz: Class<*>) {
        this[clazz] = this.getOrDefault(clazz, 0) + 1L
    }
}
