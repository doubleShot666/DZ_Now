package com.dznow.project.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dznow.project.R
import kotlinx.android.synthetic.main.language_activity_layout.*
import android.widget.Toast
import android.preference.PreferenceManager



class AppThemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_theme_activity_layout)

        val pref = PreferenceManager
            .getDefaultSharedPreferences(this)
        val themeName = pref.getString("theme", "Theme1")
        if (themeName == "Theme1") {
            setTheme(R.style.Theme1)
        } else if (themeName == "Theme2") {
            setTheme(R.style.Theme2)
            switch1.isChecked = true
        }

        validate_btn.setOnClickListener {
            when(switch1.isChecked){
                true -> setTheme(R.style.Theme2)
                false -> setTheme(R.style.Theme1)
            }
            finishView()
        }
    }

    fun finishView() {

        Toast.makeText(applicationContext,"Application du thème réussi",Toast.LENGTH_SHORT).show()

        val intentMainActivity = Intent(applicationContext,MainActivity::class.java)
        intentMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intentMainActivity)
    }
}