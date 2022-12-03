package timestamp.api.plugins

import io.ktor.application.*
import io.ktor.routing.*
import timestamp.api.routes.currentTimestamp

fun Application.configureRouting() {

    routing {
        currentTimestamp()
    }
}