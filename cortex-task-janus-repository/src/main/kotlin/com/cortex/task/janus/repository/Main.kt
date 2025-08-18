package com.cortex.task.janus.repository

import com.cortext.common.models.TaskId
import com.cortext.common.models.TaskModel
import com.cortext.common.models.TaskStatus
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal
import org.apache.tinkerpop.gremlin.process.traversal.step.util.WithOptions
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter.*
import java.util.Date
import java.util.Locale
import kotlin.sequences.map
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.time.toJavaInstant


@OptIn(ExperimentalTime::class)
fun main() {
    val g: GraphTraversalSource = traversal().withRemote("conf/remote-graph.properties")
    println("start")
//    val task = TaskModel(
//        id = TaskId("00010"),
//        label = "Task",
//        title = "Task10",
//        description = "todo",
//        createdAt = Clock.System.now(),
//        status = TaskStatus.ACTIVE
//    )
//
//    with(task) {
//        g.addV(label)
//            .property("id", id.asString())
//            .property("title", title)
//            .property("description", description)
//            .property("status", status.toString())
//            .property("create_at", Date.from(createdAt.toJavaInstant()))
//            .next()
//    }

//    val result = g.V()
//        .hasLabel("Task")
//        .elementMap<Any>()
//        .asSequence()
//        .map { vertex ->
//            println("vertex: $vertex")
//            TaskModel(
//                uuid = TaskId(vertex.getFirstAsString("uuid")),
//                label = "Task",
//                title = vertex.getFirstAsString("title"),
//                description = vertex.getFirstAsString("description"),
//                createdAt = parseToInstant(vertex.getFirstAsString("create_at")),
//                status = TaskStatus.fromValue(vertex.getFirstAsString("status"))
//            )
//        }

//    val result = g.V()
//        .hasLabel("Task")
//        .elementMap<String>().asSequence()
//    println("result: ${result.toList()}")
//    println("finish")
    g.close()
}

@OptIn(ExperimentalTime::class)
fun parseToInstant(dateStr: String): Instant {
    // Удаляем квадратные скобки если они есть
    val cleanedStr = dateStr.removeSurrounding("[", "]")

    // Создаем форматтер для входного формата
    val formatter = ofPattern(
        "EEE MMM dd HH:mm:ss zzz yyyy",
        Locale.ENGLISH
    )

    // Парсим в ZonedDateTime
    val zonedDateTime = ZonedDateTime.parse(cleanedStr, formatter)

    // Конвертируем в Instant
    return Instant.fromEpochMilliseconds(
        zonedDateTime.toInstant().toEpochMilli()
    )
}

fun Map<Any, Any>.getFirstAsString(key: String): String {
    val value = this[key] ?: throw IllegalArgumentException("Missing key: $key. Available keys: ${this.keys}")
    return when (value) {
        is List<*> -> value.firstOrNull()?.toString() ?: ""
        else -> value.toString()
    }
}