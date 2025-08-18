package com.cortex.task.janus.repository

import com.cortext.common.models.KeyValidationException
import com.cortext.common.models.TaskId
import com.cortext.common.models.TaskModel
import com.cortext.common.models.TaskStatus
import com.cortext.common.repository.DbTaskRequest
import com.cortext.common.repository.DbTaskResponse
import com.cortext.common.repository.DbTasksResponse
import com.cortext.common.repository.ITaskRepository
import org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Vertex
import java.util.Date
import java.util.UUID
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.time.toJavaInstant
import kotlin.time.toKotlinInstant

class JanusGraphTaskRepository : ITaskRepository {
    private val g: GraphTraversalSource = traversal().withRemote("conf/remote-graph.properties")

    @OptIn(ExperimentalTime::class)
    override fun tasks(): DbTasksResponse = try {
        g.V().hasLabel("Task")
            .elementMap<Any>().toList()
            .map { vertex ->
                TaskModel(
                    uuid = vertex.taskIdProperty("uuid"),
                    label = "Task",
                    title = vertex.stringProperty("title"),
                    description = vertex.stringProperty("description"),
                    createdAt = vertex.dateProperty("createdAt"),
                    status = vertex.statusProperty("status")
                )
            }.let {
                DbTasksResponse(
                    success = true,
                    result = it
                )
            }
    } catch (e: KeyValidationException) {
        DbTasksResponse(e)
    } catch (e: Exception) {
        DbTasksResponse(e)
    }

    @OptIn(ExperimentalTime::class)
    override fun createTask(request: DbTaskRequest): DbTaskResponse = try {
        with(request.task) {
            g.addV("Task")
                .property("uuid", uuid.asString())
                .property("title", title)
                .property("description", description)
                .property("createdAt", Date.from(createdAt.toJavaInstant()))
                .property("status", status.toString())
        }.next().let {
            DbTaskResponse(
                success = true,
                result = it.toTaskModel()
            )
        }
    } catch (e: Exception) {
        DbTaskResponse(e)
    }

    override fun createSubtask(request: DbTaskRequest): DbTaskResponse {
        TODO("Not yet implemented")
    }

    override fun asyncCreateTasksBatch(tasks: List<TaskModel>) {
        TODO("Not yet implemented")
    }
}

inline fun <reified T> Map<Any, Any>.property(
    key: String,
    noinline validate: (Any) -> Boolean = { it is T },
    transform: (Any) -> T
): T {
    val value = this[key] ?: throw KeyValidationException(key, "Missing key: $key. Available keys: ${this.keys}")
    if (!validate(value)) throw IllegalArgumentException(
        "Value for key '$key' is not a ${T::class.simpleName}: $value (${value::class.simpleName})"
    )
    return transform(value)
}

fun Map<Any, Any>.stringProperty(key: String): String =
    property(key, transform = { it as String })


fun Map<Any, Any>.taskIdProperty(key: String): TaskId =
    property(key, validate = { it is UUID }) { TaskId(it as UUID) }


fun Map<Any, Any>.statusProperty(key: String): TaskStatus =
    property(key, validate = { it is String }) { TaskStatus.fromValue(it as String) }


@OptIn(ExperimentalTime::class)
fun Map<Any, Any>.dateProperty(key: String): Instant =
    property(key, validate = { it is Date }) { (it as Date).toInstant().toKotlinInstant() }

@OptIn(ExperimentalTime::class)
fun Vertex.toTaskModel() = TaskModel(
    TaskId(this.value<UUID>("uuid")),
    label = this.label(),
    title = this.value("title"),
    description = this.value("description"),
    createdAt = this.value<Date>("createdAt").toInstant().toKotlinInstant(),
    status = TaskStatus.fromValue(this.value("status"))
)