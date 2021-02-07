

val add1 = "0101101"
val add2 = "111101"

fun direct( arg1: String, arg2: String): String {
    val len = Math.max( arg1.length, arg2.length )
    val a1 = arg1.padStart(len, '0' )
    val a2 = arg2.padStart(len, '0')

    var s = ""
    var carry = '0'
    for( i in len-1 downTo 0 ){
        val cnt = listOf(carry,a1[i],a2[i]).filter { it == '1' }.count()
        when( cnt ) {
            0 -> '0' to '0'
            1 -> '0' to '1'
            2 -> '1' to '0'
            3 -> '1' to '1'
            else -> throw IllegalStateException()
        }.let { (c, nc) ->
            carry = c
            s = "$nc$s"
        }
    }

    return "$carry$s"
}

direct( add1, add2 )

var a = 0
var b = 0
