val input = listOf(1,2,3,5,1,2,5,5,1,5)
val K = 5

fun directApproach( k: Int, data: List<Int>): Int {
    var num = 0
    var runLen = 0

    input.forEach {
        if (it >= k) {
            if (runLen > 0)
                num++

            runLen = 0
        } else {
            runLen++
        }
    }

    return num + (if ( runLen > 0) 1 else 0)
}

directApproach( K, input )

fun inlineApproach( k: Int, data: List<Int> ): Int =
    input.fold( 0 to 0){ (n,rl), e ->
        when {
            e >= k -> when {
                rl > 0 -> n+1 to 0
                else -> n to 0
            }
            else -> n to rl+1
        }
    }.first

inlineApproach( K, input)
