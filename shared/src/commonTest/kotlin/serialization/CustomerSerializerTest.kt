package serialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import com.loneoaktech.tests.shoppinglist.shared.model.Color
import com.loneoaktech.tests.shoppinglist.shared.model.ColorHexSerializer
import com.loneoaktech.tests.shoppinglist.shared.model.ColorRgbSerializer
import kotlin.test.Test
import kotlin.test.assertEquals

class CustomerSerializerTest {

    @Serializable
    data class IntWrapper(val color: Color)

    @Serializable
    data class HexWrapper(
        @Serializable( with = ColorHexSerializer::class )
        val color: Color
    )

    @Serializable
    data class RgbWrapper(
        @Serializable( with = ColorRgbSerializer::class )
        val color: Color
    )

    private val wInt = IntWrapper( color = Color(0x010203 ) )
    private val wIntExpectedJson = """{"color":66051}"""

    private val wHex = HexWrapper( color = Color( 0x010203) )
    private val wHexExpectedJson = """{"color":"#010203"}"""

    private val wRgb = RgbWrapper( color = Color(0x010203) )
    private val wRgbExpectedJson = """{"color":{"r":1,"g":2,"b":3}}"""

    @Test
    fun colorAsInt() {

        val w1Json = Json.encodeToString( IntWrapper.serializer(), wInt )
        println("Encoded as int: $w1Json")
        assertEquals( wIntExpectedJson, w1Json)

        val wIntDecoded = Json.decodeFromString( IntWrapper.serializer(), w1Json )
        assertEquals( wInt, wIntDecoded )
    }

    @Test
    fun colorAsHex() {
        val wHexJson = Json.encodeToString( HexWrapper.serializer(), wHex )
        println("Encoded as hex: $wHexJson")
        assertEquals( wHexExpectedJson, wHexJson)

        val wHexDecoded = Json.decodeFromString( HexWrapper.serializer(), wHexJson )
        println("Decoded from hex: $wHexDecoded")

        assertEquals( wHex, wHexDecoded)
    }

    @Test
    fun colorAsRGB() {
        val wRgbJson = Json.encodeToString( RgbWrapper.serializer(), wRgb )
        println( "Encoded as rgb: $wRgbJson" )
        assertEquals( wRgbExpectedJson, wRgbJson )

        val wRgbDecoded = Json.decodeFromString( RgbWrapper.serializer(), wRgbJson )
        assertEquals( wRgb, wRgbDecoded )
    }
}