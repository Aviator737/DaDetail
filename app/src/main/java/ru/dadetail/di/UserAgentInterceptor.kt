package ru.dadetail.di

import okhttp3.Interceptor
import okhttp3.Response

class UserAgentInterceptor: Interceptor {
    private val androidVersion = android.os.Build.VERSION.RELEASE
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .header(
                    name = "User-Agent",
                    value = "Mozilla/5.0 (Linux; Android $androidVersion) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.5563.115 Mobile Safari/537.36"
                )
                .build()
        )
    }
}