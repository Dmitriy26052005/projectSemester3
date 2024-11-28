plugins {
    kotlin("jvm") version "2.0.20"
}

group = "ie.setu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation ("io.github.oshai:kotlin-logging-jvm:7.0.0")
    implementation ("org.jetbrains.kotlin:kotlin-test:1.9.0")
    implementation ("org.junit.jupiter:junit-jupiter:5.10.0")
    implementation("org.slf4j:slf4j-simple:2.1.0-alpha1")
    implementation("com.thoughtworks.xstream:xstream:1.4.18")
    implementation("org.codehaus.jettison:jettison:1.4.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(16)
}