plugins {
    kotlin("jvm") version "2.0.10"
    id("org.jetbrains.dokka") version "1.9.20"
    //breaks gradle jvmToolchain id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
    jacoco
    application
}

group = "ie.setu"
version = "1.0"

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

tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get()
            .filter { it.name.endsWith("jar") }
            .map { zipTree(it) }
    })
}