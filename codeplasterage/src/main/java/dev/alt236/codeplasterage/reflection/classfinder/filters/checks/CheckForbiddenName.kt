package dev.alt236.codeplasterage.reflection.classfinder.filters.checks

class CheckForbiddenName : ForbiddenCheck {
    override fun isForbidden(clazz: Class<*>): Boolean =
        when {
            clazz.name.contains("\$\$") -> true
            clazz.simpleName.startsWith("_") -> true
            EXACT_NAMES.matchesExactly(clazz.simpleName) -> true
            REGEX_SIMPLE_NAME.matchesAny(clazz.simpleName) -> true
            else -> false
        }

    private companion object {
        val REGEX_SIMPLE_NAME =
            listOf(
                Regex(".*_ComponentTreeDeps"),
                Regex(".*_GeneratedInjector"),
                Regex(".*_HiltComponents"),
                Regex(".*_HiltModules"),
                Regex(".*_HiltModules_BindsModule"),
                Regex(".*_HiltModules_KeyModule"),
                Regex(".*_MembersInjector"),
                Regex(".*_ProvideFactory"),
                Regex(".*_SingletonC"),
                Regex(".*_TestComponentDataSupplier"),
                Regex("_test_.*"),
                Regex("Dagger.*Component"),
                Regex("Hilt_.*"),
                Regex("Module_.*Factory"),
            )

        val EXACT_NAMES =
            listOf(
                "BR",
                "Builder", // It's tricky to spot builders, so this is a bit naive
                "BuildConfig",
                "DataBinderMapperImpl",
            )
    }

    private fun List<String>.matchesExactly(input: String): Boolean {
        for (string in this) {
            if (string == input) {
                return true
            }
        }
        return false
    }

    private fun List<Regex>.matchesAny(input: String): Boolean {
        for (regex in this) {
            if (regex.matches(input)) {
                return true
            }
        }
        return false
    }
}
