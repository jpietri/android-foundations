package com.pahada.foundations

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

abstract class MockWebServerTest : GerkhinTest() {

    protected val mockWebServer = MockWebServer()

    private val moshiConverter: MoshiConverterFactory = MoshiConverterFactory.create(
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    )

    protected lateinit var retrofit: Retrofit

    @BeforeEach
    fun initWebServer() {
        mockWebServer.start()
        retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(moshiConverter)
            .build()
    }

    @AfterEach
    fun shutdownWebServer() = mockWebServer.shutdown()

}