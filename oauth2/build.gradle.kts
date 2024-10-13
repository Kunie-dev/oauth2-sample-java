plugins {
    java
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "dev.kunie"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    group = "dev.kunie"
    version = "0.0.1-SNAPSHOT"

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(project(":data"))

        // Spring Boot
        implementation("org.springframework.boot:spring-boot-starter")

        // Devtool
        developmentOnly("org.springframework.boot:spring-boot-devtools")

        // Annotation
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        annotationProcessor("org.projectlombok:lombok")

        // Test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

subprojects {
    dependencies {
        implementation(project(":oauth2"))

        // Spring Boot
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-web") {
            exclude("org.springframework.boot", "spring-boot-starter-tomcat")
        }
        implementation("org.springframework.boot:spring-boot-starter-undertow") {
            exclude("io.undertow", "undertow-websockets-jsr")
        }

        // Spring Boot Extra
        implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")

        // Test
        testImplementation("org.springframework.security:spring-security-test")
    }
}

project("authorization-server") {
    dependencies {
        // Spring Boot
        implementation("org.springframework.boot:spring-boot-starter-oauth2-authorization-server")
    }
}

project("client") {
    dependencies {
        // Spring Boot
        implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    }
}

project("resource-server") {
    dependencies {
        // Spring Boot
        implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    }
}
