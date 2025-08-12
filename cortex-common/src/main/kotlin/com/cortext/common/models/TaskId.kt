package com.cortext.common.models

@JvmInline
value class TaskId(private val value: String) {
    fun asString() = value

    companion object {
        val NONE = TaskId("")
    }
}