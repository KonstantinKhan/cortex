package com.cortext.common.models

import java.util.UUID
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@JvmInline
value class TaskId(private val value: String) {

    constructor(uuid: UUID) : this(uuid.toString())

    fun asString() = value

    @OptIn(ExperimentalUuidApi::class)
    fun asUUID() = Uuid.parse(value)

    companion object {
        val NONE = TaskId("")
    }
}