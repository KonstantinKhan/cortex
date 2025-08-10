package com.cortex.app

import com.cortex.app.config.AppKtorConfig
import com.cortex.mapping.toTask
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(config: AppKtorConfig) {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        post("/createTask") {
            call.createTask(config)
        }
        get("/tasks") {
            val tasks = config.taskRepository.tasks().map { it.toTask() }
            call.respond(tasks)
        }
    }
}
