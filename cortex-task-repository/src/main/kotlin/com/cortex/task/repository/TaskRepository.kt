package com.cortex.task.repository

import com.cortext.common.ITaskRepository
import com.cortext.common.models.Task
import com.cortext.common.models.TaskStatus
import com.cortext.common.requests.SubTaskRequest
import org.neo4j.driver.AuthTokens
import org.neo4j.driver.GraphDatabase
import org.neo4j.driver.QueryConfig
import org.neo4j.driver.types.Node
import java.time.ZoneOffset

class TaskRepository(password: String) : ITaskRepository {
    private val driver = GraphDatabase.driver(
        "bolt://localhost:7687",
        AuthTokens.basic("neo4j", password)
    )

    override fun createTask(task: Task): List<Node> {
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
        val parameters = mapOf(
            "id" to task.id,
            "title" to task.title,
            "description" to task.description,
            "created_at" to task.createdAt.atZone(ZoneOffset.UTC),
            "status" to task.status.toString()
        )
        return try {
            driver
                .executableQuery(query)
                .withParameters(parameters)
                .execute()
                .records()
                .map {
                    it.get("t")
                        .asNode()
                }
        } catch (e: Exception) {
            throw RuntimeException("Failed to create task", e)
        }
    }

    override fun createSubtask(request: SubTaskRequest): Node {
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
                "parentId" to parentId,
                "childId" to child.id,
                "title" to child.title,
                "description" to child.description,
                "created_at" to child.createdAt.atZone(ZoneOffset.UTC),
                "status" to child.status.toString(),
                "parentStatus" to TaskStatus.BLOCKED.toString()
            )
        }
        return try {
            driver.executableQuery(query)
                .withParameters(parameters)
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .execute()
                .records().map {
                    it.get("child").asNode()
                }.first()
        } catch (e: Exception) {
            throw RuntimeException("Failed to create subtask", e)
        }
    }

    override fun asyncCreateTasksBatch(tasks: List<Task>) {
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
                "id" to task.id,
                "title" to task.title,
                "description" to task.description,
                "created_at" to task.createdAt.toString()
            )
        }))
            .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
            .execute()
    }

    override fun tasks(): List<Node> =
        driver.executableQuery(
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
                    .asNode()
            }
}



