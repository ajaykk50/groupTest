package com.example.groupsapp.netWorking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.groupsapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val title = "Groups"
        val spannableString = SpannableString(title)
        val colorSpan = ForegroundColorSpan(ContextCompat.getColor(this, R.color.white))
        spannableString.setSpan(colorSpan, 0, title.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Apply the spannable string to the action bar title
        supportActionBar?.title = spannableString

        supportActionBar?.setDisplayShowTitleEnabled(true)

        val btnBack: ImageView = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            onBackPressedClick()
        }
    }

    fun onBackPressedClick() {
        // Your implementation here
        super.onBackPressed()
    }
}