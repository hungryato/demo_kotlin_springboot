package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}

@RestController
class MessageController {
	@GetMapping("/")
	fun index() = listOf(
		Message("1", "Hello!"),
		Message("2", "Bonjour!"),
		Message("3", "Privet!"),
	)
}

data class Message(val id: String?, val text: String)

/**
 * Constructor argument and dependency injection - (val db: JdbcTemplate)
 *
 * A class in Kotlin has a primary constructor.
 * It can also have one or more secondary constructors.
 * The primary constructor is a part of the class header, and it goes after the class name and optional type parameters.
 * In our case, the constructor is (val db: JdbcTemplate).
 * val db: JdbcTemplate is the constructor's argument:
 * ```
 * @Service
 * class MessageService(val db: JdbcTemplate)
 * ```
 */

/**
 * Trailing lambda and SAM conversion
 *
 * The findMessages() function calls the query() function of the JdbcTemplate class.
 * The query() function takes two arguments: an SQL query as a String instance, and a callback that will map one object per row:
 * ```
 * db.query("...", RowMapper { ... })
 * ```
 * The RowMapper interface declares only one method, so it is possible to implement it via lambda expression by omitting the name of the interface.
 * The Kotlin compiler knows the interface that the lambda expression needs to be converted to because you use it as a parameter for the function call.
 * This is known as SAM conversion in Kotlin:
 * ```
 * db.query("...", { ... })
 * ```
 * After the SAM conversion, the query function ends up with two arguments: a String at the first position, and a lambda expression at the last position.
 * According to the Kotlin convention, if the last parameter of a function is a function, then a lambda expression passed as the corresponding argument can be placed outside the parentheses.
 * Such syntax is also known as trailing lambda:
 * ```
 * db.query("...") { ... }
 * ```
 */

/**
 * Underscore for unused lambda argument
 *
 * For a lambda with multiple parameters, you can use the underscore _ character to replace the names of the parameters you don't use.
 * Hence, the final syntax for query function call looks like this:
 * ```
 * db.query("select * from messages") { response, _ ->
 *     Message(response.getString("id"), response.getString("text"))
 * }
 * ```
 */
@Service
class MessageService(val db: JdbcTemplate) {
	fun findMessages(): List<Message> = db.query("select * from messages") { response, _ ->
		Message(response.getString("id"), response.getString("text"))
	}

	fun save(message: Message) {
		db.update(
			"insert into messages values (?, ?)",
			message.id, message.text
		)
	}
}