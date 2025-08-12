package com.cortext.common.repository

interface ITaskResponse <T> {
    val success: Boolean
    val result: T
}