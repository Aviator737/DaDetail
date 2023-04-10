package ru.dadetail.di

import okhttp3.Interceptor
import okhttp3.Response
import ru.dadetail.BuildConfig

class ApiV1AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalUrl = chain.request().url
        val segments = originalUrl.pathSegments
        val url = originalUrl.newBuilder()
        if (segments.contains("v1")) {
            url.addQueryParameter("token", BuildConfig.API_V1_TOKEN)
        }
        val request = chain
            .request()
            .newBuilder()
            .url(url.build())
            .build()
        return chain.proceed(request)
    }
}