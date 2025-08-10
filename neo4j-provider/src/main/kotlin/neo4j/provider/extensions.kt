package neo4j.provider

import neo4j.provider.dsl.QueryBuilder

fun create(block: QueryBuilder.() -> Unit) =
    QueryBuilder(CypherOperation.CREATE).apply(block).build()
