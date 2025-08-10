package com.cortex.app.config

import com.cortex.task.repository.TaskRepository
import com.cortext.common.ITaskRepository
import io.ktor.server.application.ApplicationEnvironment

class AppKtorConfig(
    val taskRepository: ITaskRepository = TaskRepository("")
) {
    constructor(environment: ApplicationEnvironment) : this(
        taskRepository = TaskRepository(environment.config.property("ktor.dbpass").getString())
    )
}