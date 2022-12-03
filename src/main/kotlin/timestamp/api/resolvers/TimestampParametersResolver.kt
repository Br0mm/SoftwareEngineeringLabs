package timestamp.api.resolvers

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
}