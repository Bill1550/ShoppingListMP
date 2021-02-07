import kotlinx.browser.document
import me.billh.shared.Platform

fun main() {
    document.writeln("Hello, Sandy Hook!")
    document.writeln( "<br/>Platform=${Platform().platform}")
}