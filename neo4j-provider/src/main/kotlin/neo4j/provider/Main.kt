package neo4j.provider

import kotlinx.coroutines.future.await
import kotlinx.coroutines.runBlocking
import neo4j.provider.dsl.QueryResult
import org.neo4j.driver.AuthTokens
import org.neo4j.driver.GraphDatabase
import org.neo4j.driver.async.AsyncSession
import kotlin.use

fun main(args: Array<String>) {
    val result = create {
        labels {
            add("One")
            add("Two")
        }
        properties {
            add("title" to "Задача 1")
            add("description" to "Что-то сделать")
        }
    }

    when (result) {
        is QueryResult.Success -> execute(args[0], result)
        is QueryResult.Error -> {
            return
        }
    }
}

fun execute(password: String, queryResult: QueryResult.Success) {
    val driver = GraphDatabase.driver(
        "bolt://localhost:7687",
        AuthTokens.basic("neo4j", password)
    )
    runBlocking {
        driver.use { drv ->
            drv.session(AsyncSession::class.java)
                .executeWriteAsync { tx ->
                    tx.runAsync(
                        queryResult.query,
                        queryResult.properties
                    )
                        .thenCompose { it.consumeAsync() }
                }.await()
        }
    }
}


//runBlocking {
//    driver.use { drv ->
//        val result = drv.session(AsyncSession::class.java)
//            .executeWriteAsync { tx ->
//                tx.runAsync(
//                    $$"""
//                        CREATE (n:Task)
//                        SET n.properties = $properties
//                        RETURN id(n), properties(n)
//                        """.trimMargin(),
//                    mapOf("properties" to "properties")
//                )
//                    .thenCompose { it.singleAsync() }
//                    .thenApply { record ->
//                        record["id(n)"].asLong()
//                    }
//            }.await()
//        println("result: $result")
//    }
//}


