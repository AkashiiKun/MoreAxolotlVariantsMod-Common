import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("dev.kikugie.stonecutter")
    id("dev.isxander.modstitch.base")

    kotlin("jvm")

    id("me.modmuss50.mod-publish-plugin")
    `maven-publish`

    id("org.ajoberstar.grgit")
}

// version stuff
val mcVersion = property("mcVersion")!!.toString()
val mcSemverVersion = stonecutter.current.version
val versionWithoutMC = property("modVersion")!!.toString()

val isAlpha = "alpha" in versionWithoutMC
val isBeta = "beta" in versionWithoutMC

// loader stuff
val isFabric = modstitch.isLoom
val isNeoforge = modstitch.isModDevGradleRegular
val isForge = modstitch.isModDevGradleLegacy
val isForgeLike = modstitch.isModDevGradle
val loader = when {
    isFabric -> "fabric"
    isNeoforge -> "neoforge"
    isForge -> "forge"
    else -> error("Unknown loader")
}

modstitch {
    minecraftVersion = mcVersion
    javaVersion = if (stonecutter.eval(stonecutter.current.version, ">=26.1")) {
        25
    } else {
        21
    }

    parchment {
        prop("parchment.version") { mappingsVersion = it }
        prop("parchment.minecraft") { minecraftVersion = it }
    }

    metadata {
        fun prop(property: String, block: (String) -> Unit) {
            prop(property, ifNull = {""}) { block(it) }
        }

        prop("modId") { modId = it }
        prop("modName") { modName = it }
        modVersion = "$versionWithoutMC+${stonecutter.current.project}"
        modGroup = "io.github.akashiikun"
        prop("modDescription") { modDescription = it }
        modLicense = "ARR"
        modAuthor = "AkashiiKun"

        prop("githubProject") { replacementProperties.put("github", it) }
        prop("meta.mcDep") { replacementProperties.put("mc", it) }
        prop("meta.loaderDep") { replacementProperties.put("loaderVersion", it) }
        prop("deps.fabricApi") { replacementProperties.put("fapi", it) }
        prop("deps.mavapi") { replacementProperties.put("mavapi", it) }
    }

    loom {
        prop("deps.fabricLoader", required = true) { fabricLoaderVersion = it }

        configureLoom {
            runConfigs.all {
                ideConfigGenerated(false)
            }

            mixin.useLegacyMixinAp = false
        }
    }

    moddevgradle {
        prop("deps.neoforge") { neoForgeVersion = it }
        prop("deps.forge") { forgeVersion = it }

        configureNeoForge {
            runs {
                register("modClient") {
                    client()
                    sourceSet = sourceSets.main
                    gameDirectory = layout.projectDirectory.dir("../../run")
                }
            }

            mods {
                register("main") {
                    sourceSet(sourceSets.main.get())
                }
            }
        }
    }

    mixin {
        addMixinsToModManifest = true

        configs.register("mavm")
        if (isNeoforge && stonecutter.eval(stonecutter.current.version, ">=1.21.6")) {
            configs.register("mavm-neoforge")
        }
    }
}

stonecutter {
    constants {
        put("fabric", modstitch.isLoom)
        put("neoforge", modstitch.isModDevGradleRegular)
        put("forge", modstitch.isModDevGradleLegacy)
        put("forgelike", modstitch.isModDevGradle)
    }

    dependencies {
        put("fapi", (findProperty("deps.fabricApi")?.toString() ?: "0.0.0"))
        put("mavapi", (findProperty("deps.mavapi")?.toString() ?: "0.0.0"))
    }

    replacements {
        string {
            direction = eval(current.version, ">=1.21.11")
            replace("ResourceLocation", "Identifier")
        }
        string {
            direction = eval(current.version, ">=1.21.11")
            replace("import net.minecraft.Util;", "import net.minecraft.util.Util;")
        }
        string {
            direction = eval(current.version, ">=26.1")
            replace("FabricTrackedDataRegistry", "FabricEntityDataRegistry")
        }
    }
}

dependencies {
    fun Dependency?.jij() = this?.also(::modstitchJiJ)

    prop("deps.mixinExtras") {
        when {
            isFabric -> modstitchImplementation("io.github.llamalad7:mixinextras-fabric:$it").jij()
            isNeoforge -> implementation("io.github.llamalad7:mixinextras-neoforge:$it").jij()
            isForge -> {
                compileOnly("io.github.llamalad7:mixinextras-common:$it")
                implementation("io.github.llamalad7:mixinextras-forge:$it").jij()
            }
            else -> error("Unknown loader")
        }
    }

    fun modDependency(
        id: String,
        artifactGetter: (String) -> String,
        requiredByDependants: Boolean = false,
        supportsRuntime: Boolean = true,
        extra: (Boolean) -> Unit = {}
    ) {
        prop("deps.$id") { modVersion ->
            val noRuntime = prop("deps.$id.noRuntime") { it.toBoolean() } == true
            require(noRuntime || supportsRuntime) { "No runtime is not supported for $id" }

            val configuration = if (requiredByDependants) {
                if (noRuntime) "modstitchModCompileOnlyApi" else "modstitchModApi"
            } else {
                if (noRuntime) "modstitchModCompileOnly" else "modstitchModImplementation"
            }

            configuration(artifactGetter(modVersion))

            extra(!noRuntime)
        }
    }

    modDependency("mavapi", { "maven.modrinth:mavapi:${it}" })

    if (isFabric) {
        modDependency("fabricApi", { "net.fabricmc.fabric-api:fabric-api:$it" }, requiredByDependants = true)

        modDependency("fabricLangKotlin", { "net.fabricmc:fabric-language-kotlin:${it}" })
    }
    if (isNeoforge) {
        //modstitchModRuntimeOnly("thedarkcolour:kotlinforforge-neoforge:${findProperty("deps.kotlinForForge")}")
    }
//
//    listOf(
//        "imageio:imageio-core",
//        "imageio:imageio-webp",
//        "imageio:imageio-metadata",
//        "common:common-lang",
//        "common:common-io",
//        "common:common-image",
//    ).forEach {
//        modstitchApi("com.twelvemonkeys.$it:${findProperty("deps.imageio")}")
//            .jij()
//    }
//
//    listOf(
//        "json",
//        "gson"
//    ).forEach {
//        modstitchApi("org.quiltmc.parsers:$it:${findProperty("deps.quiltParsers")}")
//            .jij()
//    }
}

val releaseModVersion by tasks.registering {
    group = "mavm/versioned"

    dependsOn("publishMods")

    if (!project.publishMods.dryRun.get()) {
        //dependsOn("publishModPublicationToFILLTHISINReleasesRepository")
    }
}
createActiveTask(releaseModVersion)

val finalJarTasks = listOf(
    modstitch.finalJarTask
)
val buildAndCollect by tasks.registering(Copy::class) {
    group = "mavm/versioned"

    finalJarTasks.forEach { jar ->
        dependsOn(jar)
        from(jar.flatMap { it.archiveFile })
    }

    into(rootProject.layout.buildDirectory.dir("finalJars"))
}
createActiveTask(buildAndCollect)

java {
    withSourcesJar()
}

publishMods {
    dryRun.set(false)

    displayName.set("$versionWithoutMC ($mcVersion ${if (loader == "fabric") "Fabric" else "NeoForge"})")

    file = modstitch.finalJarTask.flatMap { it.archiveFile }

    fun versionList(prop: String) = findProperty(prop)?.toString()
        ?.split(',')
        ?.map { it.trim() }
        ?: emptyList()

    // modrinth and curseforge use different formats for snapshots. this can be expressed globally
    val stableMCVersions = versionList("pub.stableMC")

    changelog = rootProject.file("changelog.md").readText()
    type = when {
        isAlpha -> ALPHA
        isBeta -> BETA
        else -> STABLE
    }

    modLoaders.add(loader)

    val modrinthId: String by project
    if (modrinthId.isNotBlank() && hasProperty("modrinth.token")) {
        modrinth {
            projectId.set(modrinthId)
            accessToken.set(findProperty("modrinth.token")?.toString())
            minecraftVersions.addAll(stableMCVersions)
            minecraftVersions.addAll(versionList("pub.modrinthMC"))

            announcementTitle = "Download $mcVersion for ${loader.replaceFirstChar { it.uppercase() }} from Modrinth"
            requires { slug.set("mavapi") }
            if (isFabric) {
                requires { slug.set("fabric-api") }
            }
        }
    }

    val curseforgeId: String by project
    if (curseforgeId.isNotBlank() && hasProperty("curseforge.token")) {
        curseforge {
            projectId = curseforgeId
            projectSlug = findProperty("curseforgeSlug")?.toString() ?: error("curseforgeSlug property not found")
            accessToken = findProperty("curseforge.token")?.toString()
            minecraftVersions.addAll(stableMCVersions)
            minecraftVersions.addAll(versionList("pub.curseMC"))

            announcementTitle = "Download $mcVersion for ${loader.replaceFirstChar { it.uppercase() }} from CurseForge"
            requires { slug.set("mavapi") } 
            if (isFabric) {
                requires { slug.set("fabric-api") }
            }
        }
    }
}
publishing {
    publications {
        register<MavenPublication>("mod") {
            groupId = "io.github.akashiikun"
            artifactId = "mavm"
            version = modstitch.metadata.modVersion.get()

            from(components["java"])
        }
    }

    repositories {
        // todo do we need a maven repo
    }
}

tasks {
    withType<KotlinCompile> {
        compilerOptions {
            jvmTarget = modstitch.javaVersion.map { JvmTarget.fromTarget(it.toString()) }
        }

        dependsOn("stonecutterGenerate")
    }
}

tasks.named("generateModMetadata") {
    dependsOn("stonecutterGenerate")
}
modstitch.moddevgradle {
    modstitch.onEnable {
        tasks.named("createMinecraftArtifacts") {
            dependsOn("stonecutterGenerate")
        }
    }
}

fun <T> prop(property: String, required: Boolean = false, ifNull: () -> String? = { null }, block: (String) -> T?): T? {
    return ((System.getenv(property) ?: findProperty(property)?.toString())
        ?.takeUnless { it.isBlank() }
        ?: ifNull())
        .let { if (required && it == null) error("Property $property is required") else it }
        ?.let(block)
}

fun createActiveTask(
    taskProvider: TaskProvider<*>? = null,
    taskName: String? = null,
    internal: Boolean = false
): String {
    val taskExists = taskProvider != null || taskName!! in tasks.names
    val task = taskProvider ?: taskName?.takeIf { taskExists }?.let { tasks.named(it) }
    val taskName = when {
        taskProvider != null -> taskProvider.name
        taskName != null -> taskName
        else -> error("Either taskProvider or taskName must be provided")
    }
    val activeTaskName = "${taskName}Active"

    if (stonecutter.current.isActive) {
        rootProject.tasks.register(activeTaskName) {
            group = "mavm${if (internal) "/versioned" else ""}"

            task?.let { dependsOn(it) }
        }
    }

    return activeTaskName
}
