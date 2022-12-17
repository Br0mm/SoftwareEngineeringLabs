package timestamp.api

import io.ktor.client.request.*
import io.ktor.server.testing.*
import io.ktor.client.statement.*
import io.ktor.http.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class Tests {

    @Test
    fun defaultTimestampTest() = testApplication {
        val response = client.get("/timestamp")
        assertEquals(HttpStatusCode.OK, response.status)

        val regex = Regex("""^\d{4}-\d{2}-\d{2}T(\d{2}:){2}\d{2}(.\d+)?$""")

        assertEquals(
            true,
            response.bodyAsText().contains(regex),
        )
    }

    @Test
    fun timestampInGivenFormatTest() = testApplication {
        val response = client.get("/timestamp?format=yyyy-MM-dd HH-mm")
        assertEquals(HttpStatusCode.OK, response.status)

        val regex = Regex("""^\d{4}-\d{2}-\d{2}\s\d{2}-\d{2}$""")

        assertEquals(
            true,
            response.bodyAsText().contains(regex),
        )
    }

    @Test
    fun timestampFormattingToISOTest() = testApplication {
        val response = client.get("/timestamp?timestamp=1391547061000")
        assertEquals(HttpStatusCode.OK, response.status)

        assertEquals(
            "2014-02-04T20:51:01",
            response.bodyAsText(),
        )
    }

    @Test
    fun timestampFormattingToGivenFormatAndTimezoneTest() = testApplication {
        val response = client.get(
            "/timestamp?format=YYYY-MM-dd%20HH-mm-ss&time_zone=Europe/Moscow&timestamp=1391547061000"
        )
        assertEquals(HttpStatusCode.OK, response.status)

        assertEquals(
            "2014-02-04 23-51-01",
            response.bodyAsText(),
        )
    }

    @Test
    fun timestampConversionToMillisecondsTest() = testApplication {
        val response = client.get(
            "/convertTimestamp?format=yyyy-MM-dd%20HH-mm-ss&time_zone=UTC&timestamp=2014-02-04%2023-51-01"
        )
        assertEquals(HttpStatusCode.OK, response.status)

        assertEquals(
            "1391557861000",
            response.bodyAsText(),
        )
    }

    @Test
    fun invalidFormatParameterTest() = testApplication {
        val format = "invalid"
        var response = client.get(
            "/timestamp?format=$format"
        )
        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertEquals(
            "Innvalid timestamp format: $format",
            response.bodyAsText(),
        )

        response = client.get(
            "/convertTimestamp?format=$format"
        )
        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertEquals(
            "Innvalid timestamp format: $format",
            response.bodyAsText(),
        )
    }

    @Test
    fun invalidTimezoneParameterTest() = testApplication {
        val timezone = "123"
        var response = client.get(
            "/timestamp?time_zone=$timezone"
        )
        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertEquals(
            "Innvalid time zone: $timezone",
            response.bodyAsText(),
        )

        response = client.get(
            "/convertTimestamp?format=yyyy-MM-dd%20HH-mm-ss&time_zone=$timezone"
        )
        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertEquals(
            "Innvalid time zone: $timezone",
            response.bodyAsText(),
        )
    }

    @Test
    fun invalidTimestampParameterTest() = testApplication {
        val timestamp = "invalid"
        var response = client.get(
            "/timestamp?format=YYYY-MM-dd%20HH-mm-ss&time_zone=Europe/Moscow&timestamp=$timestamp"
        )
        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertEquals(
            "Innvalid timestamp: $timestamp",
            response.bodyAsText(),
        )

        response = client.get(
            "/convertTimestamp?format=YYYY-MM-dd%20HH-mm-ss&time_zone=Europe/Moscow&timestamp=$timestamp"
        )
        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertEquals(
            "Innvalid timestamp: $timestamp",
            response.bodyAsText(),
        )
    }

    @Test
    fun timezoneParameterTest() = testApplication {
        val firstResponse = client.get("/timestamp?timestamp=1391547061000")
        assertEquals(HttpStatusCode.OK, firstResponse.status)

        assertEquals(
            "2014-02-04T20:51:01",
            firstResponse.bodyAsText(),
        )

        val secondResponse = client.get("/timestamp?time_zone=Europe/Moscow&timestamp=1391547061000")
        assertEquals(HttpStatusCode.OK, secondResponse.status)

        assertEquals(
            "2014-02-04T23:51:01",
            secondResponse.bodyAsText(),
        )

        assertNotEquals(firstResponse.bodyAsText(), secondResponse.bodyAsText())
    }
}