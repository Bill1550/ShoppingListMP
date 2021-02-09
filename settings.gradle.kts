include(":androidLib")
pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }
    
}
rootProject.name = "ShoppingListMP"


include(":androidApp")
include(":shared")
include("jsApp")
