package timestamp.api.providers

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class TimestampProvider {

    fun getCurrentTimestamp(formatString: String?, timeZone: String?): String {
        val zoneId = if (timeZone != null) ZoneId.of(timeZone)
        else ZoneId.of("UTC")

        val localDateTime = LocalDateTime.now(zoneId)

        val formatter = if (formatString != null) DateTimeFormatter.ofPattern(formatString)
        else DateTimeFormatter.ISO_DATE_TIME

        return localDateTime.format(formatter)
    }

    companion object {
        val timestampProvider = TimestampProvider()
    }
}