package com.cortext.common.models

enum class TaskStatus(private val value: String) {
    UNKNOWN("unknown"),
    INBOX("inbox"),
    PLANNED("planned"),
    ACTIVE("active"),
    BLOCKED("blocked"),
    DONE("done"),
    CANCELLED("cancelled"),
    PAUSED("paused");

    companion object {
        fun fromValue(value: String): TaskStatus {
            return entries.firstOrNull { it.value == value } ?: UNKNOWN
        }
    }

    override fun toString(): String = value
}