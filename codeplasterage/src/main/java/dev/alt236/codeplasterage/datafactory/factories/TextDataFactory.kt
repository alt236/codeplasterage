package dev.alt236.codeplasterage.datafactory.factories

import dev.alt236.codeplasterage.datafactory.DataFactoryResult
import dev.alt236.codeplasterage.datafactory.SubDataFactory
import dev.alt236.codeplasterage.ext.ClassExt.implementsOrIsAnyOf
import javax.annotation.Nonnull

class TextDataFactory(
    debug: Boolean,
) : SubDataFactory(debug) {
    override fun canCreateDataFor(clazz: Class<*>): Boolean = clazz.implementsOrIsAnyOf(EXPECTED_INTERFACES)

    override fun getDummyData(
        @Nonnull clazz: Class<*>,
    ): DataFactoryResult<*> =
        when (clazz) {
            String::class.java -> DataFactoryResult.Valid(DUMMY_STRING, clazz)
            java.lang.String::class.java -> DataFactoryResult.Valid(DUMMY_STRING, clazz)

            CharSequence::class.java -> DataFactoryResult.Valid(DUMMY_STRING, clazz)
            java.lang.CharSequence::class.java -> DataFactoryResult.Valid(DUMMY_STRING, clazz)

            StringBuilder::class.java -> DataFactoryResult.Valid(StringBuilder(), clazz)
            java.lang.StringBuilder::class.java -> DataFactoryResult.Valid(StringBuilder(), clazz)

            java.lang.StringBuffer::class.java -> DataFactoryResult.Valid(StringBuffer(), clazz)

            else -> DataFactoryResult.createUnableToCreateInstanceError(this, clazz)
        }

    private companion object {
        const val DUMMY_STRING = "FOO_DUMMY_TEXT"

        val EXPECTED_INTERFACES =
            setOf(
                CharSequence::class.java,
            )
    }
}
