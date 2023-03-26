package uk.co.alt236.codeplasterage.reflection.classfinder.filters.checks

import uk.co.alt236.codeplasterage.ext.ClassExt.isAssignableToAnyOf
import uk.co.alt236.codeplasterage.ext.NormalisedClassName

class CheckForbiddenAncestry : ForbiddenCheck {

    override fun isForbidden(clazz: Class<*>): Boolean {
        return clazz.isAssignableToAnyOf(FORBIDDEN_SUPERCLASSES_AND_INTERFACES)
    }

    companion object {
        private val FORBIDDEN_SUPERCLASSES_AND_INTERFACES = setOf(
            NormalisedClassName("dagger.internal.Factory"),
            NormalisedClassName("dagger.MembersInjector"),
            NormalisedClassName("org.greenrobot.greendao.AbstractDao"),
            NormalisedClassName("dagger.hilt.android.internal.testing.EarlySingletonComponentCreator"),
            NormalisedClassName("dagger.hilt.android.internal.testing.TestComponentDataSupplier"),
            NormalisedClassName(kotlin.jvm.internal.Lambda::class.java)
        )
    }
}
