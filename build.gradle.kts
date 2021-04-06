plugins {
    java
}

group = "com.duithape"
version = "1.0-SNAPSHOT"

group = "com.duithape"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.vertx:vertx-core:4.0.3")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("io.vertx:vertx-infinispan:4.0.3")
}

tasks.create<JavaExec>("run"){
    main = project.properties.getOrDefault("mainClass","Main") as String
    classpath = sourceSets["main"].runtimeClasspath
    systemProperties["vertx.logger-delegate-factory-class-name"] = "io.vertx.core.logging.SLF4JLogDelegateFactory"
    jvmArgs = listOf("-Djgroups.bind_addr=127.0.0.1","-Djava.net.preferIPv4Stack=true")

}

java{
    sourceCompatibility = org.gradle.api.JavaVersion.VERSION_15
}
