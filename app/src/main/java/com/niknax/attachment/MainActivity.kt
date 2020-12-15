package com.niknax.attachment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*main.setOnClickListener {
            Toast.makeText(this, R.string.btn_main, Toast.LENGTH_SHORT).show()
        }
        films.setOnClickListener {
            Toast.makeText(this, R.string.btn_films, Toast.LENGTH_SHORT).show()
        }
        series.setOnClickListener {
            Toast.makeText(this, R.string.btn_series, Toast.LENGTH_SHORT).show()
        }
        cartoons.setOnClickListener {
            Toast.makeText(this, R.string.btn_cartoons, Toast.LENGTH_SHORT).show()
        }
        news.setOnClickListener {
            Toast.makeText(this, R.string.btn_news, Toast.LENGTH_SHORT).show()
        }*/
    }
}