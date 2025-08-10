package com.cortex.task.repository

import kotlinx.coroutines.runBlocking
import java.time.Duration
import java.time.Instant

fun main(args: Array<String>): Unit = runBlocking {
    val repository = TaskRepository(args[0])
    val task1 = Task(
        id = "0001",
        title = "Task 1",
        description = "Some doing",
        createdAt = Instant.now()
            .minus(Duration.ofDays(1))

    )
    val task2 = Task(
        id = "0002",
        title = "Task 2",
        description = "Some doing yet",
        createdAt = Instant.now()

    )
    val task3 = Task(
        id = "0002",
        title = "Task 2",
        description = "Some doing yet",
        createdAt = Instant.now().plus(Duration.ofHours(2))

    )


    val records = repository.tasks()

    println(records)

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