import net.labymod.labygradle.common.extension.LabyModAnnotationProcessorExtension.ReferenceType

dependencies {
    labyProcessor()
    api(project(":api"))


    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // An example of how to add an external dependency that is used by the addon.
    // addonMavenDependency("org.jeasy:easy-random:5.0.0")
}

tasks.test {
    useJUnitPlatform()
}

labyModAnnotationProcessor {
    referenceType = ReferenceType.DEFAULT
}

tasks.withType<JavaCompile>().configureEach {
    if (name.contains("test", ignoreCase = true)) {
        options.compilerArgs.add("-proc:none")
    }
}