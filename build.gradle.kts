

plugins {
    kotlin("jvm") version "1.4.10"
    id ("org.jetbrains.kotlin.plugin.allopen") version "1.3.72"
    kotlin("plugin.serialization") version "1.4.10"
    `maven-publish`
    id ("jacoco")
}

group = "fr.convergence.proddoc.libs"
version = "1.1.1-SNAPSHOT"

// je mets ces 2 variables ici car je n'arrive pas à les mettre ailleurs
// (dans settings.gradle.kts par exemple)
val myMavenRepoUser = "myMavenRepo"
val myMavenRepoPassword ="mask"

publishing {
    repositories {
        maven {
            url = uri("https://mymavenrepo.com/repo/ah37AFHxnt3Fln1mwTvi/")
            credentials {
                username = myMavenRepoUser
                password = myMavenRepoPassword
            }
        }
        mavenLocal()
    }

    publications {
        create<MavenPublication>("MaskModel") {
            from(components["java"])
        }
    }
}

repositories {
    maven(url = "https://mymavenrepo.com/repo/OYRB63ZK3HSrWJfc2RIB/")
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.0-RC")
    implementation("org.apache.kafka:kafka-clients:2.6.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
    testImplementation("org.assertj:assertj-core:3.12.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.2")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

allOpen {
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("javax.ws.rs.Path")
}

tasks.test {
    useJUnitPlatform()
}