package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}

/**
 * Data classes - data class Message
 *
 * The main purpose of data classes in Kotlin is to hold data.
 * Such classes are marked with the data keyword, and some standard functionality and some utility functions are often mechanically derivable from the class structure.
 * In this example, you declared Message as a data class as its main purpose is to store the data.
 */

/**
 * val and var properites
 *
 * Properties in Kotlin classes can be declared either as:
 * - mutable, using the var keyword
 * - read-only, using the val keyword
 * The Message class declares two properties using val keyword, the id and text.
 * The compiler will automatically generate the getters for both of these properties.
 * It will not be possible to reassign the values of these properties after an instance of the Message class is created.
 */

/**
 * Nullable types - String?
 *
 * Kotlin provides built-in support for nullable types.
 * In Kotlin, the type system distinguishes between references that can hold null (nullable references) and those that cannot (non-nullable references).
 * For example, a regular variable of type String cannot hold null.
 * To allow nulls, you can declare a variable as a nullable string by writing String?.
 * The id property of the Message class is declared as a nullable type this time.
 * Hence, it is possible to create an instance of Message class by passing null as a value for id:
 * ```
 * Message(null, "Hello!")
 * ```
 */
data class Message(val id: String?, val text: String)

/**
 * Collections - listOf()
 *
 * The Kotlin Standard Library provides implementations for basic collection types: sets, lists, and maps.
 * A pair of interfaces represents each collection type:
 * - A read-only interface that provides operations for accessing collection elements.
 * - A mutable interface that extends the corresponding read-only interface with write operations: adding, removing, and updating its elements.
 * The corresponding factory functions are also provided by the Kotlin Standard Library to create instances of such collections.
 * In this tutorial, you use the listOf() function to create a list of Message objects.
 * This is the factory function to create a read-only list of objects: you can't add or remove elements from the list.
 * If it is required to perform write operations on the list, call the mutableListOf() function to create a mutable list instance.
 */

/**
 * Trailing comma
 *
 * A trailing comma is a comma symbol after the last item of a series of elements:
 * ```
 * Message("3", "Privet!"),
 * ```
 * This is a convenient feature of Kotlin syntax and is entirely optional – your code will still work without them.
 * In the example above, creating a list of Message objects includes the trailing comma after the last listOf() function argument.
 */
@RestController
class MessageController {
	@GetMapping("/")
	fun index() = listOf(
		Message("1", "Hello!"),
		Message("2", "Bonjour!"),
		Message("3", "Privet!"),
	)
}