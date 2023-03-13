package uk.co.alt236.codeplasteragetestapp

private const val CHEVRON = ">>>>"

object TestInputLogger {

    @JvmStatic
    fun log(vararg args: Any?) {
        val sb = StringBuilder()
        sb.append("$CHEVRON Input [${args.size} arg(s)] for '${getCallingContext()}' was:")
        for ((index, arg) in args.withIndex()) {
            sb.append("\n${CHEVRON}\t[$index] type='${arg.getClassName()}', value='${arg.asString()}'")
        }
        println(sb)
    }

    private fun getCallingContext(): String {
        val element = Thread.currentThread().stackTrace[3]
        return element.className + "#" + element.methodName
    }

    private fun Any?.asString(): String {
        return when (this) {
            is Array<*> -> this.contentToString()
            else -> this.toString()
        }
    }

    private fun Any?.getClassName(): String {
        return when (this) {
            null -> "???"
            else -> this::class.java.simpleName
        }
    }
}
