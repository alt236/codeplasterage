package dev.alt236.codeplasterage.datafactory.factories

import dev.alt236.codeplasterage.datafactory.DataFactoryResult
import dev.alt236.codeplasterage.datafactory.SubDataFactory
import java.io.Closeable
import java.io.InputStream
import java.io.Serializable
import java.math.BigDecimal
import java.math.BigInteger
import java.net.URL
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass
import kotlin.time.Duration

internal class ObjectDataFactory(
    boolean: Boolean,
    private val primitiveFactory: PrimitiveDataFactory,
) : SubDataFactory(boolean) {
    override fun canCreateDataFor(clazz: Class<*>) = true // This is our fallback

    @Suppress("RemoveRedundantQualifierName")
    override fun getDummyData(clazz: Class<*>): DataFactoryResult<*> =
        when (clazz) {
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

            BigDecimal::class.java ->
                DataFactoryResult.Valid(
                    dev.alt236.codeplasterage.datafactory.factories.ObjectDataFactory.Companion.DUMMY_BIGDECIMAL,
                    clazz,
                )
            BigInteger::class.java ->
                DataFactoryResult.Valid(
                    dev.alt236.codeplasterage.datafactory.factories.ObjectDataFactory.Companion.DUMMY_BIGINTEGER,
                    clazz,
                )

            UUID::class.java ->
                DataFactoryResult.Valid(
                    dev.alt236.codeplasterage.datafactory.factories.ObjectDataFactory.Companion.DUMMY_UUID,
                    clazz,
                )
            java.util.UUID::class.java ->
                DataFactoryResult.Valid(
                    dev.alt236.codeplasterage.datafactory.factories.ObjectDataFactory.Companion.DUMMY_UUID,
                    clazz,
                )

            URL::class.java ->
                DataFactoryResult.Valid(
                    dev.alt236.codeplasterage.datafactory.factories.ObjectDataFactory.Companion.DUMMY_URL,
                    clazz,
                )

            //
            // Locale/Date/Time
            //
            Date::class.java -> DataFactoryResult.Valid(Date(0), clazz)
            Locale::class.java ->
                DataFactoryResult.Valid(
                    dev.alt236.codeplasterage.datafactory.factories.ObjectDataFactory.Companion.DUMMY_LOCALE,
                    clazz,
                )
            Calendar::class.java ->
                DataFactoryResult.Valid(
                    Calendar.getInstance(dev.alt236.codeplasterage.datafactory.factories.ObjectDataFactory.Companion.DUMMY_TIME_ZONE),
                    clazz,
                )
            TimeZone::class.java ->
                DataFactoryResult.Valid(
                    dev.alt236.codeplasterage.datafactory.factories.ObjectDataFactory.Companion.DUMMY_TIME_ZONE,
                    clazz,
                )
            DateFormat::class.java -> DataFactoryResult.Valid(DateFormat.getInstance(), clazz)
            SimpleDateFormat::class.java -> DataFactoryResult.Valid(SimpleDateFormat.getInstance(), clazz)

            Duration::class.java -> DataFactoryResult.Valid(Duration.ZERO, clazz)
            java.time.Duration::class.java -> DataFactoryResult.Valid(java.time.Duration.ZERO, clazz)

            java.util.concurrent.TimeUnit::class.java -> DataFactoryResult.Valid(TimeUnit.MILLISECONDS, clazz)
            java.util.Currency::class.java ->
                DataFactoryResult.Valid(
                    dev.alt236.codeplasterage.datafactory.factories.ObjectDataFactory.Companion.DUMMY_CURRENCY,
                    clazz,
                )

            Iterator::class.java -> DataFactoryResult.Valid(emptyList<Any>().iterator(), clazz)
            java.util.Iterator::class.java ->
                DataFactoryResult.Valid(
                    java.util.Collections
                        .emptyList<Any>()
                        .iterator(),
                    clazz,
                )

            // Streams
            Readable::class.java -> DataFactoryResult.Valid("".reader(), clazz)
            InputStream::class.java -> DataFactoryResult.Valid("".byteInputStream(), clazz)
            Closeable::class.java ->
                DataFactoryResult.Valid(
                    dev.alt236.codeplasterage.datafactory.factories.ObjectDataFactory.Companion.DUMMY_CLOSABLE,
                    clazz,
                )

            //
            // Esoteric
            //
            ClassLoader::class.java -> DataFactoryResult.Valid(this::class.java.classLoader, clazz)
            Class::class.java -> DataFactoryResult.Valid(Object::class.java, clazz)
            KClass::class.java -> DataFactoryResult.Valid(Object::class, clazz)

            java.lang.Object::class.java ->
                DataFactoryResult.Valid(
                    dev.alt236.codeplasterage.datafactory.factories.ObjectDataFactory.Companion.DUMMY_OBJECT,
                    clazz,
                )
            Any::class.java ->
                DataFactoryResult.Valid(
                    dev.alt236.codeplasterage.datafactory.factories.ObjectDataFactory.Companion.DUMMY_OBJECT,
                    clazz,
                )
            Serializable::class.java ->
                DataFactoryResult.Valid(
                    dev.alt236.codeplasterage.datafactory.factories.ObjectDataFactory.Companion.DUMMY_SERIALIZABLE,
                    clazz,
                )
            else -> {
                DataFactoryResult.createUnableToCreateInstanceError(this, clazz)
            }
        }

    private companion object {
        val DUMMY_SERIALIZABLE: Serializable = "FOO_DUMMY_SERIALIZABLE"
        val DUMMY_TIME_ZONE: TimeZone = TimeZone.getTimeZone("UTC")
        val DUMMY_OBJECT = Object()
        val DUMMY_LOCALE: Locale = Locale.US
        val DUMMY_BIGDECIMAL = BigDecimal("777.777")
        val DUMMY_BIGINTEGER = BigInteger("7777")
        val DUMMY_UUID: UUID = UUID.fromString("F7777777-7777-7777-7777-777777777777")
        val DUMMY_CURRENCY: Currency = Currency.getInstance("USD")
        val DUMMY_URL = URL("http://path.to/nowhere?right=1&yup=2")
        val DUMMY_CLOSABLE = Closeable { println("Dummy Closable is now closed!") }
    }
}
