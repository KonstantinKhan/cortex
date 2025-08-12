package com.cortex.task.repository

import com.cortext.common.models.TaskModel
import com.cortext.common.models.TaskStatus
import org.neo4j.driver.types.Node
import kotlin.time.ExperimentalTime
import kotlin.time.toKotlinInstant

@OptIn(ExperimentalTime::class)
fun Node.toTask(): TaskModel = TaskModel(
    id = get("id").asString(),
    label = this.labels().first(),
    title = get("title").asString(),
    description = get("description").asString(),
    createdAt = get("created_at").asZonedDateTime().toInstant().toKotlinInstant(),
    status = TaskStatus.fromValue(get("status").asString())
)
