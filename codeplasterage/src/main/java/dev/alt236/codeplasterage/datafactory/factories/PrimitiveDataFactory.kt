package dev.alt236.codeplasterage.datafactory.factories

import dev.alt236.codeplasterage.datafactory.DataFactoryResult
import dev.alt236.codeplasterage.datafactory.DataFactoryResult.Valid
import dev.alt236.codeplasterage.datafactory.SubDataFactory
import javax.annotation.Nonnull

class PrimitiveDataFactory(
    debug: Boolean,
) : SubDataFactory(debug) {
    override fun canCreateDataFor(clazz: Class<*>) = clazz.isPrimitive

    @Nonnull
    override fun getDummyData(
        @Nonnull clazz: Class<*>,
    ): DataFactoryResult<*> =
        when (clazz) {
            Boolean::class.java, Boolean::class.javaPrimitiveType -> Valid(DUMMY_BOOLEAN, clazz)
            Byte::class.java, Byte::class.javaPrimitiveType -> Valid(DUMMY_BYTE, clazz)
            Char::class.java, Char::class.javaPrimitiveType -> Valid(DUMMY_CHAR, clazz)
            Double::class.java, Double::class.javaPrimitiveType -> Valid(DUMMY_DOUBLE, clazz)
            Float::class.java, Float::class.javaPrimitiveType -> Valid(DUMMY_FLOAT, clazz)
            Int::class.java, Int::class.javaPrimitiveType -> Valid(DUMMY_INT, clazz)
            Long::class.java, Long::class.javaPrimitiveType -> Valid(DUMMY_LONG, clazz)
            Short::class.java, Short::class.javaPrimitiveType -> Valid(DUMMY_SHORT, clazz)

            else -> {
                DataFactoryResult.createUnableToCreateInstanceError(this, clazz)
            }
        }

    private companion object {
        const val DUMMY_BOOLEAN: Boolean = false
        const val DUMMY_BYTE: Byte = 77
        const val DUMMY_CHAR: Char = '?'
        const val DUMMY_DOUBLE: Double = 777.777
        const val DUMMY_FLOAT: Float = 777.777F
        const val DUMMY_INT: Int = 777
        const val DUMMY_LONG: Long = 7777L
        const val DUMMY_SHORT: Short = 77
    }
}
