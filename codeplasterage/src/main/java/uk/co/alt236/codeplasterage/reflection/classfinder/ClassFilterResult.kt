package uk.co.alt236.codeplasterage.reflection.classfinder

sealed interface ClassFilterResult {
    object Include : ClassFilterResult

    sealed interface Exclude : ClassFilterResult {
        fun getReason(): String = this::class.java.simpleName

        object IsPrivate : Exclude
        object IsAbstract : Exclude
        object IsProxy : Exclude
        object IsInterface : Exclude
        object IsAnnotation : Exclude
        object IsSynthetic : Exclude
        object CannotLoadMethods : Exclude
        object ForbiddenClassAncestry : Exclude
        object ForbiddenClassAnnotation : Exclude
        object ForbiddenClassName : Exclude
        object ForbiddenMethodAnnotation : Exclude
        object PartOfLibraryPackage : Exclude

        data class Custom(private val reason: String) : Exclude {
            override fun getReason(): String {
                return reason
            }
        }
    }
}
