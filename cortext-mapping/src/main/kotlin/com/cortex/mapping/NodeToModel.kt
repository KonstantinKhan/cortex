package com.cortex.mapping

import com.cortext.common.models.Task
import com.cortext.common.models.TaskStatus
import org.neo4j.driver.types.Node
import java.time.Instant

fun Node.toTask(): Task = Task(
    id = get("id").asString(),
    title = get("title").asString(),
    description = get("description").asString(),
    createdAt = Instant.from(get("created_at").asZonedDateTime()),
    status = TaskStatus.fromValue(get("status").asString())
)
