package serialization

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class BasicSerializationTest {

    @Serializable
    private data class Primitives(
        val boolean: Boolean,
        val int: Int,
        val long: Long,
        val string: String,
        val float: Float,
        val double: Double
    )

    @Serializable
    private data class SomeDefaults(
        val int1: Int = 42,
        val int2: Int,

        @Required
        val s1:String ="Hello"
    )

    @Serializable
    private data class Validated(
        val int1: Int, // must be > 1
        val int2: Int
    ) {
        init {
            require( int1 > 1 ){ "Int1 must be > 1, found: $int1"}

        }
    }

    @Test
    fun serializePrimitives() {

        val p1 = Primitives(
            boolean =  true,
            int = 123,
            long = 123456789,
            string = "Some string data",
            float = 1.1f,
            double = 2.2
        )

        val p1Expected = """{"boolean":true,"int":123,"long":123456789,"string":"Some string data","float":1.1,"double":2.2}"""

        val p1json = Json.encodeToString(Primitives.serializer(), p1)

        println( "'$p1json'")
        assertEquals( p1Expected, p1json )
    }

    @Test
    fun serializeDefaults() {
        val d1 = SomeDefaults( int2=99)

        val d1Json = Json.encodeToString( SomeDefaults.serializer(), d1 )

        println( d1Json )
    }

    @Test
    fun validatedProperty() {

        val v1Json = """{"int1":2,"int2":3}"""
        val badJson = """{"int1":0,"int2":3}"""

        val v1 = Validated( 2, 3 )

        val v1Decoded = Json.decodeFromString( Validated.serializer(), v1Json )
        assertEquals( v1, v1Decoded )

        try {
            val badDecoded = Json.decodeFromString(Validated.serializer(), badJson)
        } catch (t: Throwable ) {
            println("Caught exception: ${t.message}")
        }

    }
}