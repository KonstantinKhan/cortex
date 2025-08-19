package com.cortext.common.models

import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class TaskModel @OptIn(ExperimentalTime::class) constructor(
    val uuid: TaskId = TaskId.NONE,
    val label: String,
    val title: String,
    val description: String,
    val createdAt: Instant,
    val status: TaskStatus
) {
    companion object {
        @OptIn(ExperimentalTime::class)
        val NONE = TaskModel(
            uuid = TaskId.NONE,
            label = "",
            title = "",
            description = "",
            createdAt = Clock.System.now(),
            status = TaskStatus.UNKNOWN
        )
    }
}