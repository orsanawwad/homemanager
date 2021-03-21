plugins {
    id("homemanager.kotlin-application-conventions")
}

application {
    // Define the main class for the application.
    mainClass.set("homemanager.app.AppKt")
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("io.javalin:javalin:3.13.3")
    implementation("org.slf4j:slf4j-simple:1.8.0-beta4")
    implementation("com.beust:klaxon:5.5")
}