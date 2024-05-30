package kr.co.lion.application.usekakaomapapi

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kr.co.lion.application.usekakaomapapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.io.encoding.Base64

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofitWork()

    }

    private fun retrofitWork(){
        val service = RetrofitInstance.retrofitService
        val categoryGroupCode = "CE7"
        val x = "127.0589"
        val y = "37.52625"
        val radius = 2000

        service.getSearchKeyword(RetrofitInstance.API_KEY, categoryGroupCode, x, y, radius)
            .enqueue(object : retrofit2.Callback<ResultKeyword>{
                override fun onResponse(
                    call: Call<ResultKeyword>,
                    response: Response<ResultKeyword>
                ) {
                    Log.d("test1234", "${response.raw()}")
                    Log.d("test1234", "${response.body()}")
                }

                override fun onFailure(call: Call<ResultKeyword>, t: Throwable) {
                    Log.e("test1234", "${t.message}")
                }

            })
    }

}