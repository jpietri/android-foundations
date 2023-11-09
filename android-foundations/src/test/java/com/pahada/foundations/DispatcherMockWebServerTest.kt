package com.pahada.foundations

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

/**
 * For a map (URL -> response) based mocked webserver test, subclass this class
 * and provide the map in [responseMap].
 */
abstract class DispatcherMockWebServerTest : MockWebServerTest() {

    abstract val responseMap: Map<String, MockedResponse>

    private val responseDispatcher = object : Dispatcher() {

        override fun dispatch(request: RecordedRequest): MockResponse {
            if (responseMap.containsKey(request.path)) {
                val response = responseMap[request.path]!!
                d("MockWebServer responded: $response")
                return MockResponse().setResponseCode(response.code).setBody(response.body)
            } else {
                d("MockWebServer could not respond to: $request")
                throw IllegalAccessException("Incorrect query: ${request.path}")
            }
        }

    }

    init {
        mockWebServer.dispatcher = responseDispatcher
    }

}

data class MockedResponse(
    val code: Int = 200,
    val body: String = ""
)