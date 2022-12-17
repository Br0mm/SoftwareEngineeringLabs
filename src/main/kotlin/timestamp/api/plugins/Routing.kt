package timestamp.api.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import timestamp.api.routes.convertTimestamp
import timestamp.api.routes.timestamp

fun Application.configureRouting() {

    routing {
        timestamp()
        convertTimestamp()
    }
}