plugins {
    kotlin("jvm") version "2.3.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    implementation(kotlin("stdlib-jdk8"))
}


kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}