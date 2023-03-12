package uk.co.alt236.codeplasterage.datafactory.factories

import uk.co.alt236.codeplasterage.datafactory.DataFactoryResult
import uk.co.alt236.codeplasterage.datafactory.SubDataFactory
import uk.co.alt236.codeplasterage.ext.ClassExt.implementsAnyOf
import java.util.*
import javax.annotation.Nonnull

class CollectionDataFactory(debug: Boolean) : SubDataFactory(debug) {

    override fun canCreateDataFor(clazz: Class<*>): Boolean {
        // println("Interfaces: ($clazz): " + clazz.getAllInterfaces())
        return clazz.implementsAnyOf(COLLECTION_INTERFACES)
    }

    override fun getDummyData(@Nonnull clazz: Class<*>): DataFactoryResult<*> {
        return when (clazz) {
            ArrayList::class.java -> DataFactoryResult.Valid(ArrayList<Any>(), clazz)
            Vector::class.java -> DataFactoryResult.Valid(Vector<Any>(), clazz)
            Stack::class.java -> DataFactoryResult.Valid(Stack<Any>(), clazz)
            List::class.java -> DataFactoryResult.Valid(ArrayList<Any>(), clazz)

            HashSet::class.java -> DataFactoryResult.Valid(HashSet<Any>(), clazz)
            TreeSet::class.java -> DataFactoryResult.Valid(TreeSet<Any>(), clazz)
            Set::class.java -> DataFactoryResult.Valid(HashSet<Any>(), clazz)

            HashMap::class.java -> DataFactoryResult.Valid(HashMap<Any, Any>(), clazz)
            TreeMap::class.java -> DataFactoryResult.Valid(TreeMap<Any, Any>(), clazz)
            Map::class.java -> DataFactoryResult.Valid(HashMap<Any, Any>(), clazz)

            Iterable::class.java -> DataFactoryResult.Valid(emptyList<Any>(), clazz)
            else -> {
                val instantiationAttempt = tryToInstantiate(clazz)
                if (instantiationAttempt == null) {
                    DataFactoryResult.createUnableToCreateInstanceError(this, clazz)
                } else {
                    DataFactoryResult.Valid(instantiationAttempt, clazz)
                }
            }
        }
    }

    private companion object {
        val COLLECTION_INTERFACES = setOf(
            Collection::class.java,
            Iterable::class.java,
            Map::class.java
        )
    }
}
