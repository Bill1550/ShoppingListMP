package platform

import me.billh.shared.Platform
import kotlin.test.Test

class PlatformTests {

    @Test
    fun getPlatformName() {
        val platform = Platform()
        println("Platform=${platform.platform}")
    }
}