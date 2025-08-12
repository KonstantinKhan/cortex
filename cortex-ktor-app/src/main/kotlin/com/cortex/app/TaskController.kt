package com.cortex.app

import com.cortex.app.config.AppKtorConfig
import com.cortex.mapping.toDTO
import com.cortex.mapping.toModel
import com.cortex.task.repository.toTask
import com.cortex.transport.models.Node
import com.cortex.transport.models.TaskDTO
import com.cortext.common.repository.DbTaskRequest
import com.cortext.common.requests.SubTaskRequest
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
suspend fun ApplicationCall.createTask(config: AppKtorConfig) {
//    println(receive<String>())
    val request: TaskDTO = try {
        receive<TaskDTO>()
    } catch (e: Exception) {
        println(e)
        TaskDTO(
            id = "",
            label = "",
            title = "",
            description = "",
            createAt = Clock.System.now(),
            status = "",
        )
    }
    val response = config.taskRepository.createTask(DbTaskRequest(request.toModel()))
    respond(response.result.toDTO())
}

suspend fun ApplicationCall.createSubTask(config: AppKtorConfig) = try {
    val request = receive<SubTaskRequest>()
    val response = config.taskRepository
        .createSubtask(
            SubTaskRequest(
                request.parentId,
                request.child
            )
        ).toTask()
    respond(response)
} catch (e: Exception) {
    throw RuntimeException(e.message)
}