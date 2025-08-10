package com.cortext.common.models

enum class TaskStatus(private val value: String) {
    EMPTY("empty"),
    INBOX("inbox"),
    PLANNING("planning"),
    DONE("done"),
    PAUSE("pause");

    companion object {
        fun fromValue(value: String): TaskStatus {
            return entries.firstOrNull { it.value == value } ?: EMPTY
        }
    }

    override fun toString(): String = value
}