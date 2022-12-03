package timestamp.api.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import timestamp.api.providers.TimestampProvider
import timestamp.api.resolvers.TimestampParametersResolver

const val FORMAT_KEY = "format"
const val TIME_ZONE_KEY = "time_zone"

fun Route.currentTimestamp() {
    get("/currentTimestamp") {
        val respond = parseCurrentTimestampRespond(call.parameters)

        call.respond(
            respond.first,
            respond.second,
        )
    }
}

private fun parseCurrentTimestampRespond(parameters: Parameters): Pair<HttpStatusCode, String> {
    val format = parameters[FORMAT_KEY]
    val timeZone = parameters[TIME_ZONE_KEY]
    if (format != null && !TimestampParametersResolver.isValidFormat(format))
        return HttpStatusCode.BadRequest to "Innvalid timestamp format: $format"

    if (timeZone != null && !TimestampParametersResolver.isValidTimeZone(timeZone))
        return HttpStatusCode.BadRequest to "Innvalid time zone: $timeZone"

    return HttpStatusCode.OK to TimestampProvider.timestampProvider.getCurrentTimestamp(format, timeZone)
}