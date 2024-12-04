package dev.alt236.codeplasterage.reflection.classfinder

sealed interface ClassFilterResult {
    data object Include : ClassFilterResult

    sealed interface Exclude : ClassFilterResult {
        fun getReason(): String = this::class.java.simpleName

        data object IsPrivate : Exclude

        data object IsAbstract : Exclude

        data object IsProxy : Exclude

        data object IsInterface : Exclude

        data object IsAnnotation : Exclude

        data object IsSynthetic : Exclude

        data object CannotLoadMethods : Exclude

        data object ForbiddenClassAncestry : Exclude

        data object ForbiddenClassAnnotation : Exclude

        data object ForbiddenClassName : Exclude

        data object ForbiddenMethodAnnotation : Exclude

        data object PartOfLibraryPackage : Exclude

        data class Custom(
            private val reason: String,
        ) : Exclude {
            override fun getReason(): String = reason
        }
    }
}
