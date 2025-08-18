package com.cortex.app

import com.cortex.app.config.AppKtorConfig
import com.cortex.mapping.toDTO
import com.cortex.transport.models.ErrorDTO
import com.cortex.transport.models.TaskResponse
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
            with(tasks) {
                TaskResponse(
                    result = result.map { it.toDTO() },
                    errors = errors.map { ErrorDTO(it.message, it.field) }
                )
            }.let { call.respond(it) }
        }
    }
}
