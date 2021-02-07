val a1 = "101101"
val a2 = "111101"


fun direct( arg1: String, arg2: String): String {
    val len = max( arg1.length, arg2.length )
    val a1 = arg1.padStart(len, '0' )
    val a2 = arg2.padStart(len, '0')

    return a1.foldRightIndexed( '0' to "") {i, c, (carry, s) ->
        when(listOf(carry,c,a2[i]).filter { it == '1' }.count()){
            0 -> '0' to '0'
            1 -> '0' to '1'
            2 -> '1' to '0'
            3 -> '1  to '1'
        }.let { (newCarry, d) ->
            newCarry to d+s
        }
    }
}



