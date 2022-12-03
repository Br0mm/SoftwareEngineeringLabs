package timestamp.api.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import timestamp.api.providers.TimestampProvider
import timestamp.api.resolvers.TimestampParametersResolver

const val FORMAT_KEY = "format"
const val TIME_ZONE_KEY = "time_zone"
const val TIMESTAMP_KEY = "timestamp"

fun Route.timestamp() {
    get("/timestamp") {
        val respond = parseCurrentTimestampRespond(call.parameters)

        call.respond(
            respond.first,
            respond.second,
        )
    }
}

fun Route.convertTimestamp() {
    get("/convertTimestamp") {
        val respond = parseConvertTimestampRespond(call.parameters)

        call.respond(
            respond.first,
            respond.second,
        )
    }
}

private fun parseCurrentTimestampRespond(parameters: Parameters): Pair<HttpStatusCode, String> {
    val format = parameters[FORMAT_KEY]
    val timeZone = parameters[TIME_ZONE_KEY]
    val timestamp = parameters[TIMESTAMP_KEY]
    if (format != null && !TimestampParametersResolver.isValidFormat(format))
        return HttpStatusCode.BadRequest to "Innvalid timestamp format: $format"

    if (timeZone != null && !TimestampParametersResolver.isValidTimeZone(timeZone))
        return HttpStatusCode.BadRequest to "Innvalid time zone: $timeZone"

    if (timestamp != null)
        return if (TimestampParametersResolver.isValidTimestamp(timestamp)) {
            HttpStatusCode.OK to TimestampProvider.timestampProvider.convertTimestamp(format, timeZone, timestamp.toLong())
        }
        else  {
            HttpStatusCode.BadRequest to "Innvalid timestamp: $timestamp"
        }

    return HttpStatusCode.OK to TimestampProvider.timestampProvider.getCurrentTimestamp(format, timeZone)
}

private fun parseConvertTimestampRespond(parameters: Parameters): Pair<HttpStatusCode, String> {
    val format = parameters[FORMAT_KEY]
    val timeZone = parameters[TIME_ZONE_KEY]
    val timestamp = parameters[TIMESTAMP_KEY]
    if (format == null || !TimestampParametersResolver.isValidFormat(format))
        return HttpStatusCode.BadRequest to "Innvalid timestamp format: $format"

    if (timeZone == null || !TimestampParametersResolver.isValidTimeZone(timeZone))
        return HttpStatusCode.BadRequest to "Innvalid time zone: $timeZone"

    if (timestamp == null || !TimestampParametersResolver.isValidFormattedTimestamp(timestamp, format))
        return HttpStatusCode.BadRequest to "Innvalid timestamp: $timestamp"

    return HttpStatusCode.OK to TimestampProvider.timestampProvider.convertFormattedTimestamp(format, timeZone, timestamp)
}