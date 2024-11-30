plugins {

    kotlin("jvm") version "1.7.10"
    id("org.jetbrains.dokka") version "1.6.10"
    jacoco
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
    application
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
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.9.20")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}
kotlin {
    jvmToolchain(16)
}