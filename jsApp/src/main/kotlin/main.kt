import kotlinx.browser.document
import com.loneoaktech.tests.shoppinglist.shared.Platform

fun main() {
    document.writeln("Hello, Sandy Hook!")
    document.writeln( "<br/>Platform=${Platform().platform}")
}