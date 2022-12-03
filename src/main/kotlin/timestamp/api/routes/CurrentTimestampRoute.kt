package timestamp.api.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import timestamp.api.providers.TimestampProvider

fun Route.currentTimestamp() {
    get("/currentTimestamp") {
        call.respond(
            HttpStatusCode.OK,
            TimestampProvider.timestampProvider.getCurrentTimestamp(),
        )
    }
}