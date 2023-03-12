package uk.co.alt236.codeplasterage.datafactory.factories

import uk.co.alt236.codeplasterage.datafactory.DataFactoryResult
import uk.co.alt236.codeplasterage.datafactory.SubDataFactory
import java.io.InputStream
import java.io.Serializable
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass
import kotlin.time.Duration

internal class ObjectDataFactory(boolean: Boolean, private val primitiveFactory: PrimitiveDataFactory) :
    SubDataFactory(boolean) {

    override fun canCreateDataFor(clazz: Class<*>) = true // This is our fallback

    @Suppress("RemoveRedundantQualifierName")
    override fun getDummyData(clazz: Class<*>): DataFactoryResult<*> {
        return when (clazz) {
            java.lang.String::class.java -> DataFactoryResult.Valid(DUMMY_STRING, clazz)
            java.lang.CharSequence::class.java -> DataFactoryResult.Valid(DUMMY_STRING, clazz)

            //
            // Numbers
            //
            java.lang.Boolean::class.java -> primitiveFactory.getDummyData(Boolean::class.java)
            kotlin.Boolean::class.java -> primitiveFactory.getDummyData(Boolean::class.java)

            java.lang.Byte::class.java -> primitiveFactory.getDummyData(Byte::class.java)
            kotlin.Byte::class.java -> primitiveFactory.getDummyData(Byte::class.java)

            java.lang.Character::class.java -> primitiveFactory.getDummyData(Char::class.java)
            kotlin.Char::class.java -> primitiveFactory.getDummyData(Char::class.java)

            java.lang.Double::class.java -> primitiveFactory.getDummyData(Double::class.java)
            kotlin.Double::class.java -> primitiveFactory.getDummyData(Double::class.java)

            java.lang.Float::class.java -> primitiveFactory.getDummyData(Float::class.java)
            kotlin.Float::class.java -> primitiveFactory.getDummyData(Float::class.java)

            java.lang.Integer::class.java -> primitiveFactory.getDummyData(Int::class.java)
            kotlin.Int::class.java -> primitiveFactory.getDummyData(Int::class.java)

            java.lang.Long::class.java -> primitiveFactory.getDummyData(Long::class.java)
            kotlin.Long::class.java -> primitiveFactory.getDummyData(Long::class.java)

            java.lang.Short::class.java -> primitiveFactory.getDummyData(Short::class.java)
            kotlin.Short::class.java -> primitiveFactory.getDummyData(Short::class.java)

            BigDecimal::class.java -> DataFactoryResult.Valid(DUMMY_BIGDECIMAL, clazz)
            BigInteger::class.java -> DataFactoryResult.Valid(DUMMY_BIGINTEGER, clazz)

            //
            // Locale/Date/Time
            //
            Date::class.java -> DataFactoryResult.Valid(Date(0), clazz)
            Locale::class.java -> DataFactoryResult.Valid(DUMMY_LOCALE, clazz)
            Calendar::class.java -> DataFactoryResult.Valid(Calendar.getInstance(DUMMY_TIME_ZONE), clazz)
            TimeZone::class.java -> DataFactoryResult.Valid(DUMMY_TIME_ZONE, clazz)
            java.time.Duration::class.java -> DataFactoryResult.Valid(Duration.ZERO, clazz)
            java.util.concurrent.TimeUnit::class.java -> DataFactoryResult.Valid(TimeUnit.MILLISECONDS, clazz)

            java.util.Iterator::class.java -> DataFactoryResult.Valid(emptyList<Any>().iterator(), clazz)

            // Streams
            Readable::class.java -> DataFactoryResult.Valid("".reader(), clazz)
            InputStream::class.java -> DataFactoryResult.Valid("".byteInputStream(), clazz)

            //
            // Esoteric
            //
            ClassLoader::class.java -> DataFactoryResult.Valid(this::class.java.classLoader, clazz)
            Class::class.java -> DataFactoryResult.Valid(Object::class.java, clazz)
            KClass::class.java -> DataFactoryResult.Valid(Object::class, clazz)

            java.lang.Object::class.java -> DataFactoryResult.Valid(DUMMY_OBJECT, clazz)
            Serializable::class.java -> DataFactoryResult.Valid(DUMMY_SERIALIZABLE, clazz)
            else -> {
                DataFactoryResult.createUnableToCreateInstanceError(this, clazz)
            }
        }
    }

    private companion object {
        const val DUMMY_STRING = "FOO_DUMMY_TEXT"
        val DUMMY_SERIALIZABLE: Serializable = "FOO_DUMMY_SERIALIZABLE"
        val DUMMY_TIME_ZONE: TimeZone = TimeZone.getTimeZone("UTC")
        val DUMMY_OBJECT = Object()
        val DUMMY_LOCALE = Locale.US
        val DUMMY_BIGDECIMAL = BigDecimal("7.7")
        val DUMMY_BIGINTEGER = BigInteger("7")
    }
}
