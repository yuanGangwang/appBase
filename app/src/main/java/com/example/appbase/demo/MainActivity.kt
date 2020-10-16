package com.example.appbase.demo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import com.base.net.client.OkHttpFactory
import com.base.net.client.RetrofitClient
import com.base.net.client.RxUtils
import com.base.net.http.interceptor.BaseInterceptor
import com.example.appbase.R
import com.trello.rxlifecycle3.android.ActivityEvent
import com.trello.rxlifecycle3.components.RxActivity
import dev.common.utils.SPUtil
import dev.common.utils.ToastUtil
import dev.common.weight.setting.SettingItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : RxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Auto.setCustomDensity(this, application)
        setContentView(R.layout.activity_main)

        SPUtil.init(this, "ahah")

        findViewById<TextView>(R.id.dialog)
//            .post {
//                Log.e("wwww", "width = " + oncli.width)
//            }
            .setOnClickListener {
                startActivity(Intent(MainActivity@ this, DialogActivity::class.java))
//                start()
            }


        setting.setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }

        navi.setOnClickListener {
            startActivity(Intent(this, NaviActivity::class.java))
        }

        list.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }
        banner.setOnClickListener {
            startActivity(Intent(this, BannerActivity::class.java))
        }
        search.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        request.setOnClickListener {
            request()
        }
    }


    override fun onResume() {
        super.onResume()
        var settingOptions = SettingItem.SettingOptions.getSettingOptions("message", false)
        var choice = SettingItem.SettingOptions.getSettingOptions("notify", true)
        log("$settingOptions message")
        log("$choice notify")
    }


    private fun log(message: String) {
        Log.i("dialog", message)
    }

    override fun onStart() {
        super.onStart()
    }

    fun request() {
//        Toast.makeText(this, "啊"+i, Toast.LENGTH_SHORT).show()

//        var okHttpClient = OkHttpFactory.OkHttpFactoryBuilder().withContent(this)
//            .withDebug(true)
//            .withInterceptor(BaseInterceptor(null))
//            .withIsOpenHttps(false)
//            .withTimeOut(20).build().okHttpClient
//
//        var apiService =
//            RetrofitClient.getInstance().init("http://121.43.186.223:8300/", okHttpClient)
//                .createApiServer(ApiService::class.java)
//
//        RxUtils.wrapRestCall(apiService.homeMoreItem,null)
//            .subscribe(Consumer {
//                Log.i("As", "request: ")
//            }, Consumer {
//                Log.e("As", "Erro"+it.message)
//            })
    }

}
