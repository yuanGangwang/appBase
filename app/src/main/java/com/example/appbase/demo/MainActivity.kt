package com.example.appbase.demo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.base.net.client.RetrofitClient
import com.base.net.client.RxUtils
import com.example.appbase.R
import dev.common.utils.SPUtil
import dev.common.weight.setting.SettingItem
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
            start()
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

    private fun start() {

      RetrofitClient.getInstance("http://121.43.186.223:8300", this).initRetrofit()

        RxUtils.wrapRestCall(RetrofitClient.create(ApiService::class.java).sendOlderPhoneAddress)
            .subscribe(Consumer<String> {
                Log.e("As", "success")
            }, Consumer {
                Log.e("As", it.message)
            })
    }


}
