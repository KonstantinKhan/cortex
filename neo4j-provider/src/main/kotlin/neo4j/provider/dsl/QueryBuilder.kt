package neo4j.provider.dsl

import neo4j.provider.CypherOperation

@QueryDSLMarkers
class QueryBuilder(
    val operation: CypherOperation,
    val alias: String = "n"
) {
    private val labels: MutableList<String> = mutableListOf()
    private val properties: MutableMap<String, String> = mutableMapOf()

    fun labels(block: LabelScope.() -> Unit) {
        LabelScope(labels).apply(block)
    }

    fun properties(block: PropertyScope.() -> Unit) {
        PropertyScope(properties).apply(block)
    }

    fun build(): QueryResult {
        if (labels.isEmpty())
            return QueryResult.Error("At least one label must be provided")
        val operationPart = "$operation ($alias:${labels.joinToString(":")})"
        val setPart =
            if (properties.isEmpty())
                ""
            else {
                val clauses = properties.map { (key, _) ->
                    "$alias.$key = $$key"
                }.joinToString(", ")
                " SET $clauses"
            }
        val query = operationPart + setPart
        return QueryResult.Success(query, properties)
    }
}