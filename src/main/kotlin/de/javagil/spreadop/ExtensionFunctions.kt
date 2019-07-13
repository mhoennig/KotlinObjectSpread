package de.javagil.spreadop

import kotlin.reflect.KClass
import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

/**
 * Creates any Kotlin Data-Class by a map.
 *
 * @sample
 * <pre><code>
 *  val map = mutableMapOf("first" to "Henry", "last" to "O'Conner")
 *  val obj: SomeClass = SomeClass::class.by(map)
 * </code></pre>
 */
fun <T : Any> KClass<T>.by(values: Map<String, Any?>): T {
    val ctor = this.primaryConstructor!!
    val params = ctor.parameters.associateBy({ it }, { values.get(it.name) })
    return ctor.callBy(params)
}

/**
 * Converts public properties of any Kotlin object to a Map.
 *
 * For the case of combining properties of multiple objects and or single properties to a new objects,
 * it can replace the ES6 object spread operator `...`or Pythons unpack operator `**`.
 * Instead of `...someObject` (ES6) respectively `**someObject` (Python) you write `someObject.asMap()`.
 */
fun <T : Any> T.asMap(): Map<String, Any?> {
    return javaClass.kotlin.memberProperties
            .filter { it.visibility == KVisibility.PUBLIC }
            .map { it.name to it.getter.call(this) }
            .toMap()
}

/**
 * Invoke operator as short-variant of asMap().
 *
 * For the case of combining properties of multiple objects and or single properties to a new objects,
 * it can replace the ES6 object spread operator `...`or Pythons unpack operator `**`.
 * Instead of `...someObject` (ES6) respectively `**someObject` (Python) you write `someObject()`.
 */
operator fun Any.invoke(): Map<String, Any?> = this.asMap()
