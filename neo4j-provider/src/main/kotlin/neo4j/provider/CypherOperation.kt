package neo4j.provider

enum class CypherOperation(val value: String) {
    CREATE("CREATE"),
    MERGE("MERGE"),
    MATCH("MATCH");

    override fun toString(): String = value
}