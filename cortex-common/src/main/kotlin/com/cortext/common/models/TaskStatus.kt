package com.cortext.common.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class TaskStatus(private val value: String) {
    @SerialName("unknown")
    UNKNOWN("unknown"),
    @SerialName("inbox")
    INBOX("inbox"),
    @SerialName("planned")
    PLANNED("planned"),
    @SerialName("active")
    ACTIVE("active"),
    @SerialName("blocked")
    BLOCKED("blocked"),
    @SerialName("done")
    DONE("done"),
    @SerialName("cancelled")
    CANCELLED("cancelled"),
    @SerialName("paused")
    PAUSED("paused");

    companion object {
        fun fromValue(value: String): TaskStatus {
            return entries.firstOrNull { it.value == value } ?: UNKNOWN
        }
    }

    override fun toString(): String = value
}