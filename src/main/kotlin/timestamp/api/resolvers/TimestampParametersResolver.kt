package timestamp.api.resolvers

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

object TimestampParametersResolver {

    fun isValidFormat(formatString: String): Boolean {
        return try {
            DateTimeFormatter.ofPattern(formatString)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun isValidTimeZone(timeZoneString: String): Boolean {
        return  (TimeZone.getAvailableIDs()).contains(timeZoneString)
    }

    fun isValidTimestamp(timestampString: String): Boolean {
        return try {
            val timeInMillis = timestampString.toLong()
            Instant.ofEpochMilli(timeInMillis).atZone(ZoneId.of("UTC")).toLocalDateTime()
            true
        } catch (e: Exception) {
            false
        }
    }
}