package com.example.appbase.demo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appbase.R
import dev.common.weight.setting.SettingGroup

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        var view: SettingGroup = findViewById<SettingGroup>(R.id.settingGroup)
        view.setChangeListener {
            if (it == SettingGroup.CollapseState.down) {
                Toast.makeText(this@SettingActivity, "down", Toast.LENGTH_SHORT).show()
            }
            if (it == SettingGroup.CollapseState.up) {
                Toast.makeText(this@SettingActivity, "up", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
