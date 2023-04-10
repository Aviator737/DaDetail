package ru.dadetail.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import ru.dadetail.BuildConfig
import ru.dadetail.data.api.*

val apiModule = module {
    factory { provideOkHttpClient() }
    factory { provideCustomerApi(get()) }
    factory { provideSearchApi(get()) }
    factory { provideCartApi(get()) }
    factory { provideOrderApi(get()) }
    factory { providePositionApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideCustomerApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
fun provideSearchApi(retrofit: Retrofit): SearchApi = retrofit.create(SearchApi::class.java)
fun provideCartApi(retrofit: Retrofit): CartApi = retrofit.create(CartApi::class.java)
fun provideOrderApi(retrofit: Retrofit): OrderApi = retrofit.create(OrderApi::class.java)
fun providePositionApi(retrofit: Retrofit): PositionApi = retrofit.create(PositionApi::class.java)

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    return OkHttpClient()
        .newBuilder()
        .cookieJar(AppCookieJar())
        .addInterceptor(loggingInterceptor)
        .addInterceptor(UserAgentInterceptor())
        .addInterceptor(ApiV1AuthInterceptor())
        .build()
}
