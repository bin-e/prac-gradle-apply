/**
 * 퍼블리싱
 */
import org.gradle.api.publish.PublishingExtension

plugins {
    `maven-publish`
}

configure<PublishingExtension> {
    publishing {
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/OWNER/REPOSITORY")
                credentials {
                    username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                    password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
                }
            }
        }
        publications {
            register<MavenPublication>("gpr") {
                from(components["java"])
            }
        }
    }
}
