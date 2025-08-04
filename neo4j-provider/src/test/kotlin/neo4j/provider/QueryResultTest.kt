package neo4j.provider

import neo4j.provider.dsl.QueryBuilder
import neo4j.provider.dsl.QueryResult
import kotlin.test.Test
import kotlin.test.assertTrue

class QueryResultTest {
    @Test
    fun `returns Error when labels is empty`() {
        val actual = QueryBuilder(CypherOperation.CREATE).build()

        assertTrue {
            actual is QueryResult.Error &&
                    actual.message == "At least one label must be provided"
        }
    }
}