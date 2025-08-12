package com.cortext.common.models

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class TaskModel @OptIn(ExperimentalTime::class) constructor(
    val id: String,
    val label: String,
    val title: String,
    val description: String,
    val createdAt: Instant,
    val status: TaskStatus
)