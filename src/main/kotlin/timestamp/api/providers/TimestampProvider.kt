package timestamp.api.providers

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class TimestampProvider {

    fun getCurrentTimestamp(formatString: String? = null): String {
        val localDateTime = LocalDateTime.now(ZoneOffset.UTC)
        val formatter = if (formatString != null) DateTimeFormatter.ofPattern(formatString)
        else DateTimeFormatter.ISO_DATE_TIME
        return localDateTime.format(formatter)
    }

    companion object {
        val timestampProvider = TimestampProvider()
    }
}