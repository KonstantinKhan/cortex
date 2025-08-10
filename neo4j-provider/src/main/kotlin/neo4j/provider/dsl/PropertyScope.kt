package neo4j.provider.dsl

@QueryDSLMarkers
class PropertyScope(private val properties: MutableMap<String, String>) {
    fun add(vararg property: Pair<String, String>) {
        properties.putAll(property)
    }
}
