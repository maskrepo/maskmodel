plugins {
    kotlin("jvm") version "1.3.61"
    id ("org.jetbrains.kotlin.plugin.allopen") version "1.3.72"
    `maven-publish`
}

group = "fr.convergence.proddoc.libs"
version = "1.0.0-SNAPSHOT"

publishing {
    repositories {
        maven(url = "https://mymavenrepo.com/repo/ah37AFHxnt3Fln1mwTvi/")
        credentials {
                username myMavenRepoUser
                password myMavenRepoPassword
            }
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
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

allOpen {
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("javax.ws.rs.Path")
}
