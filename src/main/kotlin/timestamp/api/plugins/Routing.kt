package timestamp.api.plugins

import io.ktor.application.*
import io.ktor.routing.*
import timestamp.api.routes.timestamp

fun Application.configureRouting() {

    routing {
        timestamp()
    }
}