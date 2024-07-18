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
 * @RestController annotation
 *
 * You need to tell Spring that MessageController is a REST Controller, so you should mark it with the @RestController annotation.
 * This annotation means this class will be picked up by the component scan because it's in the same package as our DemoApplication class.
 */
@RestController
class MessageController {
	/**
	 * @GetMapping annotation
	 *
	 * @GetMapping marks the functions of the REST controller that implement the endpoints corresponding to HTTP GET calls.
	 */

	/**
	 * @RequestParam annotation
	 *
	 * The function parameter name is marked with @RequestParam annotation.
	 * This annotation indicates that a method parameter should be bound to a web request parameter.
	 * Hence, if you access the application at the root and supply a request parameter called "name", like /?name=<your-value>, the parameter value will be used as an argument for invoking the index() function.
	 */

	/**
	 * Single-expression functions - index()
	 *
	 * Since the index() function contains only one statement you can declare it as a single-expression function.
	 * This means the curly braces can be omitted and the body is specified after the equals sign =.
	 */

	/**
	 * Type inference for function return types
	 *
	 * The index() function does not declare the return type explicitly.
	 * Instead, the compiler infers the return type by looking at the result of the statement on the right-hand side from the equals sign =.
	 * The type of Hello, $name! expression is String, hence the return type of the function is also String.
	 */

	/**
	 * String templates - $name
	 *
	 * Hello, $name! expression is called a String template in Kotlin.
	 * String templates are String literals that contain embedded expressions.
	 * This is a convenient replacement for String concatenation operations.
	 */
	@GetMapping("/")
	fun index(@RequestParam("name") name: String) = "Hello, $name!"
}