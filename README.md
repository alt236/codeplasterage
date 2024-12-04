# CodePlastarage

Have you ever joined a project with limited code coverage and was asked to increase it as a priority? CodePlaterage will
help you reach your goal faster!

CodePlastarage is a library aiming to increase your code coverage is an automatic and completely senseless way. It will
reflectively load classes based on filters declared in the test definition and try will pass meaningless
parameters to methods in order to force the execution of codepaths.

Think about it as a fuzzy testing framework with limited fuzziness capabilities and absolutely no meaningful impact to
the improvement of code quality and confidence.

It is also _an_ answer to the common interview question: _Can you have a project with 100% code coverage and still have
bugs?_

#### Disclaimer:

In case it is not obvious: This is a joke (but fully functional) library which was born out of a discussion about how
code coverage metrics can be abused and artificially inflated.

THIS LIBRARY WILL TRY AND EXECUTE AS MUCH CODE AS POSSIBLE IN YOUR CODEBASE (AND ANY DEPENDENCIES) WITH NO MEASURE OR
REASON (UNLESS **YOU** SET UP THE FILTERS CORRECTLY), _SO **POTENTIALLY DESTRUCTIVE OR COST-INCURRING** ACTIONS **WILL**
ALSO **BE** **EXECUTED**_.

* Do not use without backups
* By using this (UNLESS **YOU** SET UP THE FILTERS CORRECTLY), and depending on how your code is structured, you may end
  up making unexpected network/db calls
* Do not use this project as a way to ensure quality or to validate any contracts or behaviour

Use at your own risk.

I bear absolutely no responsibility for any runaway (or unexpected) `Runtime.getRuntime()
.exec("rm -rf ./")` or `FileUtils.deleteDirectory(new File("./"));` or `DROP TABLE`s or similar that you get smitten
with when using this library.

I also bear no responsibility for any financial damages incurred in any way, say because 1000s of images were spun up,
or all your data were moved out/in of Glacier, or your employer/customer is wondering where his code is now, or 1000s of
push messages were sent, etc.

If you still want to use this, and you know where the destructive stuff are, you _can_ exclude any relevant code via
the `@Config` filters (see below) - I still bear no responsibility though!

## Usage

Note: There are examples in the `test` folder of the included `testapp`. There is also an example at the bottom of this
README.

### To get going you need to:

1. Add the library as a dependency to your project (see next section)
2. Create a test class - you can call it anything you want
3. Make it implement one (or more) of the following interfaces
    * `PlasterageTestEquals`
    * `PlasterageTestHashCode`
    * `PlasterageTestToString`
    * `PlasterageTests` - this is a convenience interface which implements all of the above.
4. Create the proposed methods and annotate them with `@Test`. They should be empty - any code in them will never be
   executed.
5. Annotate the class with `@RunWith(CodeplasterageTestRunner::class)`
6. Optionally (but you really _do_ want to), configure the tests by using the `@Config` annotation (see below)

### Adding the dependency:

To add this library as a dependency you need to:

* Add the github package repo in your `build.gradle`:

```groovy
repositories {
    maven {
        url = "https://maven.pkg.github.com/alt236/codeplasterage"
    }
}
```

* Add the dependency itself:

```groovy
dependencies {
    testImplementation 'uk.co.alt236:codeplasterage:{latest-version}'
}    
```

You can find the latest version here: https://github.com/alt236/codeplasterage/packages/

## Configuration

Tests can be configured on both class and method level via the `@Config` annotation.

**Note: A method level `@Config` fully overwrites the class level one (for that method only).**

```kotlin
annotation class Config(
    val includeClassNamePatterns: Array<String>,
    val excludeClassNamePatterns: Array<String>,
    val forceIncludeClassNames: Array<String>,
    val forceExcludeClassNames: Array<String>,
    val customDummyDataFactories: Array<KClass<SubDataFactory>>,
    val ignoreErrors: Boolean,
    val debug: Boolean
)
```

There are two parts in the config, the inclusion/exclusion filters and general configuration.

### Inclusion/ Exclusion filters:

There are four different filters you can use to target (and exclude classes). They can be all be used at the same time.

| Field Name                 | Priority   | Input                                | Example                                    |
|----------------------------|------------|--------------------------------------|--------------------------------------------|
| `forceIncludeClassNames`   | 1, Highest | Array of fully qualified class names | `uk.co.alt236.codeplasteragetestapp.FOO$1` |
| `forceExcludeClassNames`   | 2          | Array of fully qualified class names | `uk.co.alt236.codeplasteragetestapp.FOO$1` |
| `excludeClassNamePatterns` | 3          | Array of RegEx Pattern               | `.*codeplasteragetestapp.*`                |
| `includeClassNamePatterns` | 4, Lowest  | Array of RegEx Pattern               | `.*codeplasteragetestapp.*`                |

Note 1: It is always better to add the patterns for your own code in `includeClassNamePatterns` instead of just running
everything. Otherwise, any 3rd party libraries included in your project will be instantiated and many of them do no not
behave very nice.

Note 2: If you have any known classes that misbehave, add them in the `forceExcludeClassNames` list.

### General Configuration

| Field Name                 | Usage                                                                                                                                                                                                                         |
|----------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `ignoreErrors `            | If set to `true` any exceptions will be swallowed which will ensure that all tests are marked as a PASS                                                                                                                       |
| `debug`                    | If set to `true` it will dump A LOT of debug info in the terminal during a run. If set to `true` on the CLASS level, at the end of a run it will also list any objects that could not be created by the dummy data factories. |
| `customDummyDataFactories` | This can be used to register additional data factories to provide specialised instances (see below).                                                                                                                          |

There are also classes that are automatically excluded as there is no point in executing/testing them (or they simply
cannot be (easily) instantiated via reflection). You can find them in `SystemClassFilter.kt`.

### Custom Dummy Data Factories

Note: See `CustomDataFactoryTest` for an example implementation.

As mentioned above, this library uses reflection to instantiate and execute methods in classes. As these methods often
require parameters, a DummyDataFactory is used to produce them.
Obviously while this library will provide dummy data for common objects and primitives, it cannot provide everything,
especially classes that belong to the domain of the code being "tested".

By creating and registering said factories, one can provide instances of any objects that are needed.

An example custom DummyDataFactory can be found at the end of this README:

Custom Factory classes need to be registered in the `customDummyDataFactories` field `@Config`. **Remember that method
level configs fully overwrite class level ones.**

## Example Classes

### Example Test with configuration

```kotlin
@RunWith(CodeplasterageTestRunner::class)
@Config(
    includeClassNamePatterns = [".*codeplasteragetestapp.*"],
    ignoreErrors = true,
    debug = false
)
class SuperDuperAllTests : PlasterageTests {

    @Test
    override fun testToString() {
        // Empty by design
    }

    @Test
    override fun testHashCode() {
        // Empty by design
    }

    @Test
    override fun testEquals() {
        // Empty by design
    }

    @Test
    override fun testMethodCalling() {
        // Empty by design
    }

    @Test
    fun testMethodCalling2() {
        assertTrue(true) // This is a normal test. It will also be executed, but it cannot be configured.
    }
}
```

### Example Custom Dummy Data Factory

```kotlin
// The factory needs to have a constructor that takes a boolean and extend SubDataFactory
// The boolean reflects the `debug` value in the @Config
// Note: This factory only handles a single class, but that is not a requirement. 
private class BrandNewClassDataFactory(debug: Boolean) : SubDataFactory(debug) {

    // If this is true, getDummyData will be called. This is only an optimisation, it can be set to return `true`
    override fun canCreateDataFor(clazz: Class<*>): Boolean {
        return clazz == BrandNewClass::class.java
    }

    override fun getDummyData(clazz: Class<*>): DataFactoryResult<*> {
        return when (clazz) {
            // If there is a match, wrap the instance in a `DataFactoryResult.Valid` object
            BrandNewClass::class.java -> DataFactoryResult.Valid(BrandNewClass(), clazz)

            // If there is no match, by returning the following the next SubDataFactory will be used
            else -> DataFactoryResult.createUnableToCreateInstanceError(this, clazz)
        }
    }
}
```