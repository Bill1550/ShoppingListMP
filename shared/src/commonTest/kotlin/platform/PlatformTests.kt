package platform

import com.loneoaktech.tests.shoppinglist.shared.Platform
import kotlin.test.Test

class PlatformTests {

    @Test
    fun getPlatformName() {
        val platform = Platform()
        println("Platform=${platform.platform}")
    }
}