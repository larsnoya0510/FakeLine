package com.example.fakeline.feature_talk

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fakeline.R
import kotlinx.android.synthetic.main.activity_sort.*

class SortActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sort)
        this.title=""
        dateTextView.setOnClickListener {
            this.setResult(Activity.RESULT_OK)
            this.finish()
        }
        unreadTextView.setOnClickListener {
            this.setResult(Activity.RESULT_CANCELED)
            this.finish()
        }
    }
    override fun onBackPressed() {
//        super.onBackPressed()
    }
}
