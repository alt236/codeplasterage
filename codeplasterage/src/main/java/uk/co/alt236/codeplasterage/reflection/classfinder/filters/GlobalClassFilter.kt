package uk.co.alt236.codeplasterage.reflection.classfinder.filters

import uk.co.alt236.codeplasterage.Consts
import uk.co.alt236.codeplasterage.ext.ClassExt.canLoadMethods
import uk.co.alt236.codeplasterage.reflection.classfinder.ClassFilterResult
import uk.co.alt236.codeplasterage.reflection.classfinder.ClassFilterResult.Exclude
import uk.co.alt236.codeplasterage.reflection.classfinder.ClassFinderFilter
import uk.co.alt236.codeplasterage.reflection.classfinder.filters.checks.CheckForbiddenAncestry
import uk.co.alt236.codeplasterage.reflection.classfinder.filters.checks.CheckForbiddenClassAnnotations
import uk.co.alt236.codeplasterage.reflection.classfinder.filters.checks.CheckForbiddenMethodAnnotations
import uk.co.alt236.codeplasterage.reflection.classfinder.filters.checks.CheckForbiddenName
import java.lang.reflect.Modifier
import java.lang.reflect.Proxy

class GlobalClassFilter : ClassFinderFilter {
    private val checkClassAnnotations = CheckForbiddenClassAnnotations()
    private val checkAncestry = CheckForbiddenAncestry()
    private val checkName = CheckForbiddenName()
    private val checkMethodAnnotations = CheckForbiddenMethodAnnotations()

    override fun isIncluded(clazz: Class<*>): ClassFilterResult {
        return when {
            // We don't want any weird test loops
            clazz.`package`.name == Consts.ROOT_PACKAGE -> Exclude.PartOfLibraryPackage
            clazz.`package`.name.startsWith(Consts.ROOT_PACKAGE + ".") -> Exclude.PartOfLibraryPackage

            checkName.isForbidden(clazz) -> Exclude.ForbiddenClassName

            Modifier.isPrivate(clazz.modifiers) -> Exclude.IsPrivate
            Modifier.isAbstract(clazz.modifiers) -> Exclude.IsAbstract
            clazz.isAnnotation -> Exclude.IsAnnotation
            clazz.isInterface -> Exclude.IsInterface
            clazz.isSynthetic -> Exclude.IsSynthetic
            Proxy.isProxyClass(clazz) -> Exclude.IsProxy

            !clazz.canLoadMethods() -> Exclude.CannotLoadMethods

            checkAncestry.isForbidden(clazz) -> Exclude.ForbiddenClassAncestry
            checkClassAnnotations.isForbidden(clazz) -> Exclude.ForbiddenClassAnnotation
            checkMethodAnnotations.isForbidden(clazz) -> Exclude.ForbiddenMethodAnnotation

            else -> return ClassFilterResult.Include
        }
    }
}
