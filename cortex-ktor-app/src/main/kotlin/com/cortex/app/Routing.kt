package com.cortex.app

<<<<<<< HEAD
import com.cortex.task.repository.TaskRepository
import io.ktor.server.application.*
=======
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.defaultheaders.*
>>>>>>> origin/main
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
<<<<<<< HEAD
        post("/create") {

        }
        get("/tasks") {
        }
=======
>>>>>>> origin/main
    }
}
