package timestamp.api.providers

import java.time.*
import java.time.format.DateTimeFormatter

class TimestampProvider {

    fun getCurrentTimestamp(formatString: String?, timeZone: String?): String {
        val localDateTime = LocalDateTime.now(ZoneOffset.UTC)

        return getFormattedTimestamp(formatString, timeZone, localDateTime)
    }

    fun convertTimestamp(formatString: String?, timeZone: String?, timestamp: Long): String {
        val localDateTime = Instant.ofEpochMilli(timestamp).atZone(ZoneId.of("UTC")).toLocalDateTime()

        return getFormattedTimestamp(formatString, timeZone, localDateTime)
    }

    private fun getFormattedTimestamp(formatString: String?, timeZone: String?, localDateTime: LocalDateTime): String {
        val zoneId = if (timeZone != null) ZoneId.of(timeZone)
        else ZoneId.of("UTC")

        val zonedDateTime = ZonedDateTime.now(
            zoneId.normalized()
        )

        val newDateTime = localDateTime.plusSeconds(zonedDateTime.offset.totalSeconds.toLong())

        val formatter = if (formatString != null) DateTimeFormatter.ofPattern(formatString)
        else DateTimeFormatter.ISO_DATE_TIME

        return newDateTime.format(formatter)
    }

    companion object {
        val timestampProvider = TimestampProvider()
    }
}