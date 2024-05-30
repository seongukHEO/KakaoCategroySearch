package kr.co.lion.application.usekakaomapapi

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    const val API_KEY = "06f465bcf338fde965f436556078c53a"

    // HTTP 로깅 인터셉터
    private fun httpLoggingInterceptor() : Interceptor {
        return HttpLoggingInterceptor { message -> Log.e("MyHttp : ", message + "") }
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    // Authorization 헤더를 추가하는 인터셉터
    private fun authorizationInterceptor() : Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestWithHeaders: Request = originalRequest.newBuilder()
                .header("Authorization", "KakaoAK $API_KEY")
                .build()
            chain.proceed(requestWithHeaders)
        }
    }

    // OkHttpClient 설정
    val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor())
            .addInterceptor(authorizationInterceptor())  // Authorization 인터셉터 추가
            .build()
    }

    // Retrofit 설정
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl("https://dapi.kakao.com/")
            .build()
    }

    // KakaoAPI 서비스 생성
    val retrofitService: KakaoAPI by lazy {
        retrofit.create(KakaoAPI::class.java)
    }
}
