package com.cortex.app

import com.cortex.app.config.AppKtorConfig
import com.cortex.mapping.toDTO
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
        post("/createSubTask") {
            call.createSubTask(config)
        }
        get("/tasks") {
            val tasks = config.taskRepository.tasks()
            call.respond(tasks.result.map { model -> model.toDTO() })
        }
    }
}
