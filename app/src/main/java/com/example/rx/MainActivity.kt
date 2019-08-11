package com.example.rx

import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.example.rx.data.API
import com.example.rx.data.UsersList
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL = "https://api.github.com/search/"
    private val TAG = "RXTAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.INTERNET),
                1
            )
        }

        val rxTutorialRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val testObservable = rxTutorialRetrofit.create(API::class.java).getUserDetails("rokano")
        testObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<UsersList>> {
                override fun onSuccess(t: Response<UsersList>) {
                    val top = t.body()?.users
                    Log.d(TAG, "${top?.size}")

                    //here we can analyze our data or pass it to main thread initialize UI
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "onSubscribe called")
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "Error perfomed")
                    e.printStackTrace()
                }
            })
        Log.d(TAG, "Main thread")
    }
}
