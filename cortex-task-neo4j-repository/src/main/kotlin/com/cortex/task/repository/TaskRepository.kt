package com.cortex.task.repository

import com.cortext.common.repository.ITaskRepository
import com.cortext.common.models.TaskModel
import com.cortext.common.models.TaskStatus
import com.cortext.common.repository.DbTaskRequest
import com.cortext.common.repository.DbTaskResponse
import com.cortext.common.repository.DbTasksResponse
import org.neo4j.driver.AuthTokens
import org.neo4j.driver.GraphDatabase
import org.neo4j.driver.QueryConfig
import java.time.ZoneOffset
import kotlin.time.ExperimentalTime
import kotlin.time.toJavaInstant

class TaskRepository(password: String) : ITaskRepository {
    private val driver = GraphDatabase.driver(
        "bolt://localhost:7687",
        AuthTokens.basic("neo4j", password)
    )

    @OptIn(ExperimentalTime::class)
    override fun createTask(request: DbTaskRequest): DbTaskResponse {
        val query = $$"""
            MERGE (t:Task {
                id: $id,
                title: $title,
                description: $description,
                created_at: $created_at,
                status: $status
            })
            RETURN t
        """.trimIndent()
        val parameters = with(request) {
            mapOf(
                "id" to task.uuid.asString(),
                "title" to task.title,
                "description" to task.description,
                "created_at" to task.createdAt.toJavaInstant().atZone(ZoneOffset.UTC),
                "status" to task.status.toString()
            )
        }
        val data = try {
            driver
                .executableQuery(query)
                .withParameters(parameters)
                .execute()
                .records()
                .map {
                    it.get("t")
                        .asNode().toTask()
                }
        } catch (e: Exception) {
            throw RuntimeException("Failed to create task", e)
        }
        return DbTaskResponse(
            success = data.isNotEmpty(),
            result = data.first()
        )
    }

    @OptIn(ExperimentalTime::class)
    override fun createSubtask(request: DbTaskRequest): DbTaskResponse {
        val query = $$"""
            MATCH (parent:Task {id: $parentId})
            CREATE (child:Task {
                id: $childId, 
                title: $title, 
                description: $description, 
                created_at: $created_at,
                status: $status
                })
            SET parent.status = $parentStatus
            CREATE (child)-[:CHILD_OF]->(parent)
            RETURN child
        """.trimIndent()
        val parameters = with(request) {
            mapOf(
                "parentId" to request.relatedTaskId.asString(),
                "childId" to request.task.uuid.asString(),
                "title" to request.task.title,
                "description" to request.task.description,
                "created_at" to request.task.createdAt.toJavaInstant().atZone(ZoneOffset.UTC),
                "status" to request.task.status.toString(),
                "parentStatus" to TaskStatus.BLOCKED.toString()
            )
        }
        val data = try {
            driver.executableQuery(query)
                .withParameters(parameters)
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .execute()
                .records().map {
                    it.get("child").asNode().toTask()
                }
        } catch (e: Exception) {
            throw RuntimeException("Failed to create subtask", e)
        }
        return DbTaskResponse(
            success = data.isNotEmpty(),
            result = data.first()
        )
    }

    override fun asyncCreateTasksBatch(tasks: List<TaskModel>) {
        driver.executableQuery(
            $$"""
            UNWIND $tasks AS task
            CREATE (t:Task {
                id: task.id,
                title: task.title,
                description: task.description,
                created_at: datetime(task.created_at)
            })
            RETURN t
        """
        ).withParameters(mapOf("tasks" to tasks.map { task ->
            mapOf(
                "id" to task.uuid,
                "title" to task.title,
                "description" to task.description,
//                "created_at" to task.createdAt.toString()
            )
        }))
            .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
            .execute()
    }

    override fun tasks(): DbTasksResponse {
        val data = driver.executableQuery(
            """
                MATCH (t:Task)
                RETURN t
                LIMIT 25
            """.trimIndent()
        ).withConfig(QueryConfig.builder().withDatabase("neo4j").build())
            .execute()
            .records()
            .map {
                it.get("t")
                    .asNode().toTask()
            }
        return DbTasksResponse(
            success = data.isNotEmpty(),
            result = data
        )
    }
}



