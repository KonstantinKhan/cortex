package neo4j.provider.dsl

sealed class QueryResult {
    data class Success(val query: String, val properties: Map<String, String>): QueryResult()
    data class Error(val message: String): QueryResult()
}