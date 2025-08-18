package com.cortex.task.repository

import com.cortext.common.models.TaskModel
import com.cortext.common.models.TaskStatus
import kotlinx.coroutines.runBlocking
import java.time.Duration
import java.time.Instant

fun main(args: Array<String>): Unit = runBlocking {
    val repository = TaskRepository(args[0])


    val records = repository.tasks()
//    repository.createTask(
//        Task(
//            id = "0001", title = "Task 1", description = "todo", createdAt = Instant.now(), status = TaskStatus.ACTIVE
//
//        )
//    )

//    repository.createSubtask(
//        "0001",
//        Task(
//            id = "0002",
//            title = "Task 2",
//            description = "to-do",
//            createdAt = Instant.now(),
//            status = TaskStatus.ACTIVE
//        )
//    )

//    println(records)

//    records.forEach {
//        println(it)
//    }

//    var time: Long = 0
//    repository.warmingUp()
//    time = measureTimeMillis {
//        repository.createTask(task1)
//        repository.createSubtask(task1, task2)
//    }
//    println(time)


//    records?.forEach { record ->
//        val node = record?.get("t")?.asNode()
//        println(node?.asMap())
//    }
}