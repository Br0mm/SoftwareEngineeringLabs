package timestamp.api

import io.ktor.server.application.*
import timestamp.api.plugins.configureMonitoring
import timestamp.api.plugins.configureRouting

fun main(args: Array<String>) =
    io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureRouting()
    configureMonitoring()
}