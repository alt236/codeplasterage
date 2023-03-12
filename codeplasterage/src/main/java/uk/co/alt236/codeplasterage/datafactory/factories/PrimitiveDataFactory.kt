package uk.co.alt236.codeplasterage.datafactory.factories

import uk.co.alt236.codeplasterage.datafactory.DataFactoryResult
import uk.co.alt236.codeplasterage.datafactory.DataFactoryResult.Valid
import uk.co.alt236.codeplasterage.datafactory.SubDataFactory
import javax.annotation.Nonnull

class PrimitiveDataFactory(debug: Boolean) : SubDataFactory(debug) {
    override fun canCreateDataFor(clazz: Class<*>) = clazz.isPrimitive

    @Nonnull
    override fun getDummyData(@Nonnull clazz: Class<*>): DataFactoryResult<*> {
        return when (clazz) {
            Boolean::class.java, Boolean::class.javaPrimitiveType -> Valid(false, clazz)
            Byte::class.java, Byte::class.javaPrimitiveType -> Valid(7.toByte(), clazz)
            Char::class.java, Char::class.javaPrimitiveType -> Valid('v', clazz)
            Double::class.java, Double::class.javaPrimitiveType -> Valid(7.0, clazz)
            Float::class.java, Float::class.javaPrimitiveType -> Valid(7f, clazz)
            Int::class.java, Int::class.javaPrimitiveType -> Valid(7, clazz)
            Long::class.java, Long::class.javaPrimitiveType -> Valid(7L, clazz)
            Short::class.java, Short::class.javaPrimitiveType -> Valid(7.toShort(), clazz)

            else -> {
                DataFactoryResult.createUnableToCreateInstanceError(this, clazz)
            }
        }
    }
}
