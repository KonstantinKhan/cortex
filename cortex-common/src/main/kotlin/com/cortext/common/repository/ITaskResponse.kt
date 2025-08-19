package com.cortext.common.repository

import com.cortext.common.models.CommonErrorModel

interface ITaskResponse <T> {
    val success: Boolean
    val result: T
    val errors: List<CommonErrorModel>
}