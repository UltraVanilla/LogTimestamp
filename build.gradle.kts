import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    java
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
}

group = "lordpipe.logtimestamp"
version = "1.0.0"
description = "Log timestamp on server boot"

repositories {
    mavenCentral()
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.2-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }

    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }
}

bukkit {
    author = "lordpipe"
    main = "lordpipe.logtimestamp.LogTimestamp"
    apiVersion = "1.19"

    load = BukkitPluginDescription.PluginLoadOrder.STARTUP

    permissions {
        register("logtimestamp.login") {
            description = "Show server timestamp on login"
        }
    }
}
