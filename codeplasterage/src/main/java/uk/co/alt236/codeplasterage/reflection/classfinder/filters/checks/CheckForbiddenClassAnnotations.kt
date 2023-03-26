package uk.co.alt236.codeplasterage.reflection.classfinder.filters.checks

import org.junit.runner.RunWith
import uk.co.alt236.codeplasterage.ext.AnnotationExt.isAnnotatedWithAnyOf
import uk.co.alt236.codeplasterage.ext.NormalisedClassName

class CheckForbiddenClassAnnotations : ForbiddenCheck {

    override fun isForbidden(clazz: Class<*>): Boolean {
        return clazz.isAnnotatedWithAnyOf(FORBIDDEN_CLASS_ANNOTATIONS)
    }

    private companion object {
        private val FORBIDDEN_CLASS_ANNOTATIONS = setOf(
            NormalisedClassName("dagger.hilt.android.EarlyEntryPoint"),
            NormalisedClassName("dagger.hilt.codegen.OriginatingElement"),
            NormalisedClassName("dagger.hilt.InstallIn"),
            NormalisedClassName("dagger.hilt.internal.aggregatedroot.AggregatedRoot"),
            NormalisedClassName("dagger.hilt.internal.GeneratedEntryPoint"),
            NormalisedClassName("dagger.hilt.internal.processedrootsentinel.ProcessedRootSentinel"),
            NormalisedClassName("dagger.hilt.processor.internal.aggregateddeps.AggregatedDeps"),
            NormalisedClassName("dagger.internal.DaggerGenerated"),
            NormalisedClassName("dagger.internal.QualifierMetadata"),
            NormalisedClassName("dagger.internal.ScopeMetadata"),
            NormalisedClassName("dagger.Module"),
            NormalisedClassName("javax.annotation.Generated"),
            NormalisedClassName(RunWith::class.java)
        )
    }
}
