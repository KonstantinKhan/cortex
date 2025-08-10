package com.cortex.app

import com.cortex.task.repository.TaskRepository
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        post("/create") {

        }
        get("/tasks") {
        }
    }
}
