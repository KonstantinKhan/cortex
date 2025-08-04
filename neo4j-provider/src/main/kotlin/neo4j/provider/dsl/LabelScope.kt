package neo4j.provider.dsl

@QueryDSLMarkers
class LabelScope(private val labels: MutableList<String>) {
    fun add(vararg label: String) {
        require(label.isNotEmpty()) {"At least one label must be provided"}
        require(label.all { it.isNotBlank() }) {"Labels cannot be blank"}
        labels.addAll(label)
    }
}