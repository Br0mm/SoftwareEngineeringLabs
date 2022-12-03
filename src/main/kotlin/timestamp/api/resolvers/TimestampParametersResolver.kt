package timestamp.api.resolvers

import java.time.format.DateTimeFormatter

object TimestampParametersResolver {

    fun isValidFormat(formatString: String): Boolean {
        return try {
            DateTimeFormatter.ofPattern(formatString)
            true
        } catch (e: Exception) {
            false
        }
    }
}