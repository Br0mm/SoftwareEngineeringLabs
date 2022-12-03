package timestamp.api.providers

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class TimestampProvider {

    fun getCurrentTimestamp(): String {
        val localDateTime = LocalDateTime.now(ZoneOffset.UTC)
        return localDateTime.format(DateTimeFormatter.ISO_DATE_TIME)
    }

    companion object {
        val timestampProvider = TimestampProvider()
    }
}