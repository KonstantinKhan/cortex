package com.cortex.app.config

import com.cortex.task.janus.repository.JanusGraphTaskRepository
import com.cortext.common.repository.ITaskRepository
import io.ktor.server.application.ApplicationEnvironment

class AppKtorConfig(
    val taskRepository: ITaskRepository = ITaskRepository.NONE
) {
    constructor(environment: ApplicationEnvironment) : this(
        taskRepository = JanusGraphTaskRepository()
    )
}