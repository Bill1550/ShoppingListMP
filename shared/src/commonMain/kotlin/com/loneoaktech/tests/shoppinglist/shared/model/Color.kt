@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.loneoaktech.tests.shoppinglist.shared.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

//@Serializable
data class Color( val rgb: Int) {

    @Serializer( forClass = Color::class )
    companion object : KSerializer<Color> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Color", PrimitiveKind.STRING )

        override fun serialize(encoder: Encoder, value: Color) {
            encoder.encodeInt( value.rgb ) // encode as simple int
        }

        override fun deserialize(decoder: Decoder): Color {
            return Color( decoder.decodeInt() )
        }
    }
}

object ColorHexSerializer : KSerializer<Color> {
    private val regex = Regex("#(\\p{XDigit}{1,6})")
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("Color", PrimitiveKind.STRING )

    override fun serialize(encoder: Encoder, value: Color) {
        encoder.encodeString( "#${(value.rgb and(0xFFFFFF)).toString(16).padStart( 6, '0')}" )
    }

    override fun deserialize(decoder: Decoder): Color {
        val m = checkNotNull(regex.matchEntire( decoder.decodeString() )){"Invalid color format" }

        return Color( m.groupValues[1].toInt(16) )
    }
}

@Serializable
@SerialName("Color")
private data class ColorSurrogate( val r: Int, val g: Int, val b: Int) {
    init {
        require( r in 0..255 && g in 0..255 && b in 0..255)
    }
}

object ColorRgbSerializer : KSerializer<Color> {
    override val descriptor: SerialDescriptor = ColorSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: Color) {
        val surrogate = ColorSurrogate(
            r = (value.rgb shr 16) and 0xff,
            g = (value.rgb shr 8) and 0xff,
            b = value.rgb and 0xff
        )
        return encoder.encodeSerializableValue( ColorSurrogate.serializer(), surrogate)
    }

    override fun deserialize(decoder: Decoder): Color {
        val surgate = decoder.decodeSerializableValue( ColorSurrogate.serializer() )
        return Color( (surgate.r shl 16) or (surgate.g shl 8) or surgate.b)
    }
}

