package com.cortext.common.models

class KeyValidationException(
    val key: String,
    message: String,
    cause: Throwable? = null,
) : IllegalArgumentException(message, cause)